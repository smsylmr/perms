package com.example.perms.bean.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户和角色关联表(SysUserRole)实体类
 *
 * @author makejava
 * @since 2020-12-09 16:41:21
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -80817909427406798L;

    @TableId
    private Long id;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 角色ID
    */
    private Long roleId;


}