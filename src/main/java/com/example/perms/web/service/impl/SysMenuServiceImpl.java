package com.example.perms.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.SysMenu;
import com.example.perms.web.mapper.SysMenuMapper;
import com.example.perms.web.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * (SysMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:52
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

}