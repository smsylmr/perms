package com.example.perms.bean.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 部门表(SysDept)实体类
 *
 * @author makejava
 * @since 2020-12-09 16:39:46
 */
@Data
@TableName(value = "sys_dept")
public class SysDept {
    /**
    * 部门id
    */
    @TableId
    private Long deptId;
    /**
    * 父部门id
    */
    private Long parentId;
    /**
    * 祖级列表
    */
    private String ancestors;
    /**
    * 部门名称
    */
    private String deptName;
    /**
    * 显示顺序
    */
    private Integer orderNum;
    /**
    * 负责人
    */
    private String leader;
    /**
    * 联系电话
    */
    private String phone;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 部门状态（0正常 1停用）
    */
    private String status;
    /**
    * 删除标志（0代表存在 2代表删除）
    */
    private String delFlag;
    /**
    * 创建者
    */
    private String createBy;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;
    /**
    * 更新者
    */
    private String updateBy;
    /**
    * 更新时间
    */
    private LocalDateTime updateTime;


}