package com.example.perms.auth;

import com.alibaba.fastjson.JSON;
import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import com.example.perms.utils.JwtTokenUtils;
import com.example.perms.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Resource
    private RedisUtils redisUtils;


    /**
     * description: 登录验证成功后调用，验证成功后将生成Token，并重定向到用户主页home
     * 与AuthenticationSuccessHandler作用相同
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @return void
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象，这里是JwtAuthUser
        JwtAuthUser jwtUser = (JwtAuthUser) authResult.getPrincipal();
        log.info("用户登陆验证拦截，生成JwtAuthUser:{}",jwtUser.toString());
        List<String> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            roles.add(authority.getAuthority());
        }
        log.info("用户角色:{}",roles);
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), jwtUser.getId().toString(),roles,false);
        log.info("生成Token:{}",token);
        //设置到缓存 过期时间1小时
        redisUtils.set(jwtUser.getId().toString(),token,3600);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new Result<>(ResCode.OK,token)));

    }

    /**
     * description: 登录验证失败后调用，这里直接Json返回，实际上可以重定向到错误界面等
     * 与AuthenticationFailureHandler作用相同
     *
     * @param request
     * @param response
     * @param failed
     * @return void
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Result error = new Result(ResCode.USER_LOGIN_FAILED, failed);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        if (failed instanceof LockedException) {
            error.setMessage("账户被锁定，请联系管理员!");
        } else if (failed instanceof CredentialsExpiredException) {
            error.setMessage("密码过期，请联系管理员!");
        } else if (failed instanceof AccountExpiredException) {
            error.setMessage("账户过期，请联系管理员!");
        } else if (failed instanceof DisabledException) {
            error.setMessage("账户被禁用，请联系管理员!");
        } else if (failed instanceof BadCredentialsException) {
            error.setMessage("用户名或者密码输入错误，请重新输入!");
        }
        response.getWriter().write(JSON.toJSONString(error));
    }
}
