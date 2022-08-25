package com.example.perms.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.auth.JwtAuthUser;
import com.example.perms.bean.entity.SysUser;
import com.example.perms.bean.entity.SysUserRole;
import com.example.perms.bean.req.*;
import com.example.perms.bean.vo.SysUserVO;
import com.example.perms.utils.*;
import com.example.perms.web.mapper.SysUserMapper;
import com.example.perms.web.service.SysUserRoleService;
import com.example.perms.web.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:44:03
 */
@Service("sysUserService")
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private CurrentUserUtils currentUserUtils;
    @Resource
    private SysUserRoleService userRoleService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisUtils redisUtils;
    @Override
    public SysUser selectByName(String name) {
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",name);
        System.out.println(this.baseMapper.selectOne(queryWrapper));
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public PageUtils<SysUserVO> list(SysUserRequest sysUserRequest) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(sysUserRequest.getUserName())){
            queryWrapper.like("user_name",sysUserRequest.getUserName());
        }
        if(StrUtil.isNotEmpty(sysUserRequest.getDeptId())){
            queryWrapper.eq("dept_id",sysUserRequest.getDeptId());
        }
        if(StrUtil.isNotEmpty(sysUserRequest.getEmail())){
            queryWrapper.like("email",sysUserRequest.getEmail());
        }
        if(StrUtil.isNotEmpty(sysUserRequest.getPhoneNumber())){
            queryWrapper.like("phone_number",sysUserRequest.getPhoneNumber());
        }
        Page<SysUser> page = page(new Page<>(sysUserRequest.getCurPage(), sysUserRequest.getLimit()), queryWrapper);
        List<SysUserVO> sysUserVOS = OrikaUtils.mapAsList(page.getRecords(), SysUserVO.class);
        return new PageUtils<>(sysUserVOS,page);

    }

    @Override
    public void updateState(String userIds, UpdStateRequest request) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        String[] split = userIds.split(",");
        queryWrapper.in("user_id", Arrays.asList(split));
        List<SysUser> list = list(queryWrapper);
        list.forEach(sysUser -> {
            sysUser.setStatus(request.getEnable().equals("true")?"0":"1");
            sysUser.setUpdateTime(LocalDateTime.now());
            sysUser.setUpdateBy(currentUserUtils.getCurrentUser().getUserId().toString());
        });
        updateBatchById(list);
    }

    @Override
    public void updateUser(SysUserUpdRequest sysUser) {
        SysUser old = this.getById(sysUser.getUserId());
        OrikaUtils.map(sysUser,old);
        updateById(old);
        SysUserRole role = userRoleService.getOne(new QueryWrapper<SysUserRole>().eq("user_id", sysUser.getUserId()));
        if(sysUser.getRoleId()!=null&&!sysUser.getRoleId().equals(role.getId().toString())){
            role.setRoleId(Long.parseLong(sysUser.getRoleId()));
            userRoleService.updateById(role);
        }
    }

    @Override
    public void saveUser(SysUserUpdRequest sysUser) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        SysUser user = OrikaUtils.map(sysUser, SysUser.class);
        user.setDelFlag("0");
        user.setCreateBy(currentUserUtils.getCurrentUser().getLoginName());
        this.save(user);
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(Long.valueOf(sysUser.getRoleId()));
        userRoleService.save(userRole);
    }

    @Override
    public void delete(String userIds) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        String[] split = userIds.split(",");
        queryWrapper.in("user_id", Arrays.asList(split));
        List<SysUser> list = list(queryWrapper);
        list.forEach(sysUser -> {
            sysUser.setDelFlag("2");
            sysUser.setUpdateTime(LocalDateTime.now());
            sysUser.setUpdateBy(currentUserUtils.getCurrentUser().getUserId().toString());
        });
        updateBatchById(list);
    }

    @Override
    public void changePwd(String userId, ChangePwdRequest request) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(request.getOldPwd());
        SysUser sysUser = this.getById(userId);
        if(!encode.equals(sysUser.getPassword())){
            throw new RuntimeException("密码错误");
        }
        sysUser.setPassword(bCryptPasswordEncoder.encode(request.getOldPwd()));
        this.updateById(sysUser);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getLoginName(), loginRequest.getPassword());
        //AuthenticationContextHolder.setContext(authenticationToken);
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        JwtAuthUser jwtUser = (JwtAuthUser) authenticate.getPrincipal();
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
        return token;
    }
}