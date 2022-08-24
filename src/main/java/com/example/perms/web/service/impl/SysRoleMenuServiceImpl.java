package com.example.perms.web.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.SysRoleMenu;
import com.example.perms.web.mapper.SysRoleMenuMapper;
import com.example.perms.web.service.SysRoleMenuService;;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:43:40
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

}