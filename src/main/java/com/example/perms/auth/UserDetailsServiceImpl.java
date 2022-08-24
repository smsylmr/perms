package com.example.perms.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.perms.bean.entity.SysUser;
import com.example.perms.web.service.SysUserRoleService;
import com.example.perms.web.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", name);
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        if(sysUser==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        sysUser.setRole(sysUserRoleService.listByUserId(sysUser.getUserId()).get(0).getRoleKey());
        return new JwtAuthUser(sysUser);
    }
}
