package com.example.perms.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.perms.bean.entity.SysUser;
import com.example.perms.bean.vo.SysUserVO;
import com.example.perms.web.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author shuang.kou
 * @description 获取当前请求的用户
 */
@Component
public class CurrentUserUtils {

    @Autowired
    private SysUserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    public SysUserVO getCurrentUser() {
        SysUser one = userService.getOne(new QueryWrapper<SysUser>().eq("login_name", getCurrentUserName()));
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(one,sysUserVO);
        sysUserVO.setEnable(sysUserVO.getStatus().equals("0"));
        return sysUserVO;
    }

    private  String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }

    public void logout() {
        redisTemplate.delete(getCurrentUser().getUserId().toString());
    }
}
