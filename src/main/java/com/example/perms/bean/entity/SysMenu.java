package com.example.perms.bean.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (SysMenu)实体类
 *
 * @author makejava
 * @since 2020-12-09 16:40:27
 */
@Data
@TableName(value = "sys_menu")
public class SysMenu {

    @TableId
    private Integer menuId;
    
    private Integer pid;
    
    private String path;
    
    private String component;
    
    private String name;
    
    private String meta;
    
    private String redirect;


}