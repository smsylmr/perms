package com.example.perms.web.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.SysRole;
import com.example.perms.bean.entity.SysUserRole;
import com.example.perms.web.mapper.SysUserRoleMapper;
import com.example.perms.web.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:44:24
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public List<SysRole> listByUserId(Long userId) {
        return getBaseMapper().listByUserId(userId);
    }
}