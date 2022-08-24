package com.example.perms.auth;

import com.alibaba.fastjson.JSON;
import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import com.example.perms.utils.JwtTokenUtils;
import com.example.perms.utils.RedisUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @Resource
    private RedisUtils redisUtils;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    /**
     * description: 从request的header部分读取Token
     *
     * @param request
     * @param response
     * @param chain
     * @return void
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        log.info("===========请求进入权限验证Filter==========");
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        log.info("获取Header中的Token：{}",tokenHeader);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        //解析Token时将“Bearer ”前缀去掉
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            String previousToken = redisUtils.get(JwtTokenUtils.getId(token))
                    .toString().replace(JwtTokenUtils.TOKEN_PREFIX, "");
            if (!token.equals(previousToken)) {
                SecurityContextHolder.clearContext();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(JSON.toJSONString(new Result<>(ResCode.USER_LOGIN_REPEAT)));//                chain.doFilter(request, response);
                return;
            }
            authentication = getAuthentication(token);
        } catch (JwtException | NullPointerException e) {
            logger.info("无效的Token");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    /**
     * description: 读取Token信息，创建UsernamePasswordAuthenticationToken对象
     *
     * @param token
     * @return org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = JwtTokenUtils.getUsername(token);
        List<String> roles = JwtTokenUtils.getUserRole(token);
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (roles != null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.toUpperCase()));
            }
        }
        if (username != null) {
            log.info("用户名：{}已登录",username);
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        return null;
    }
}
