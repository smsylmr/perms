package com.example.perms.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.perms.bean.entity.SysMenu;
import com.example.perms.bean.entity.SysRole;
import com.example.perms.bean.entity.SysRoleMenu;
import com.example.perms.bean.entity.SysUser;
import com.example.perms.bean.vo.SysUserVO;
import com.example.perms.web.service.SysMenuService;
import com.example.perms.web.service.SysRoleMenuService;
import com.example.perms.web.service.SysUserRoleService;
import com.example.perms.web.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author shuang.kou
 * @description 获取当前请求的用户
 */
@Component
public class CurrentUserUtils {

    @Resource
    private SysUserService userService;
    @Resource
    private SysRoleMenuService roleMenuService;
    @Resource
    private SysMenuService menuService;
    @Resource
    private SysUserRoleService userRoleService;
    @Resource
    private RedisUtils redisUtils ;

    public SysUserVO getCurrentUser() {
        SysUser one = userService.getOne(new QueryWrapper<SysUser>().eq("login_name", getCurrentUserName()));
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(one,sysUserVO);
        List<SysRole> sysRoles = userRoleService.listByUserId(one.getUserId());
        List<SysRoleMenu> roleMenus = roleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id", sysRoles.get(0).getRoleId()));
        List<Long> menuList = roleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        List<SysMenu> menus = menuService.list(new QueryWrapper<SysMenu>().in("menu_id", menuList));
        sysUserVO.setMenuList(menus);
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
        redisUtils.del(getCurrentUser().getUserId().toString());
    }
}
