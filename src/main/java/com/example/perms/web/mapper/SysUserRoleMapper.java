package com.example.perms.web.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.perms.bean.entity.SysRole;
import com.example.perms.bean.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-09 16:47:15
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysRole> listByUserId(@Param("userId")Long userId);

}