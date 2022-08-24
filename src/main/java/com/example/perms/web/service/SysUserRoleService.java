package com.example.perms.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.perms.bean.entity.SysRole;
import com.example.perms.bean.entity.SysUserRole;
import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表服务接口
 *
 * @author makejava
 * @since 2020-12-09 16:44:24
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    List<SysRole> listByUserId(Long userId);

}