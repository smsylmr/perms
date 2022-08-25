package com.example.perms.bean.vo;

import com.example.perms.bean.entity.SysMenu;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author linmr
 * @description:
 * @date 2020/12/16
 */
@Data
public class SysUserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 登录账号
     */
    private String loginName;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户类型（00系统用户 01注册用户）
     */
    private String userType;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;
    /**
     * 头像路径
     */
    private String avatar;
    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 菜单列表
     */
    private List<SysMenu> menuList;
}
