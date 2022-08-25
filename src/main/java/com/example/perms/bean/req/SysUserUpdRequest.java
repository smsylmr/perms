package com.example.perms.bean.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author linmr
 * @description:
 * @date 2022/8/24
 */
@Data
public class SysUserUpdRequest {
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;
    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    private Long deptId;
    /**
     * 登录账号
     */
    @ApiModelProperty("登录账号")
    private String loginName;
    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String userName;
    /**
     * 用户类型（00系统用户 01注册用户）
     */
    @ApiModelProperty("用户类型（00系统用户 01注册用户")
    private String userType;
    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    private String email;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;
    /**
     * 用户性别（0男 1女 2未知）
     */
    @ApiModelProperty("用户性别（0男 1女 2未知）")
    private String sex;
    /**
     * 头像路径
     */
    @ApiModelProperty("头像路径")
    private String avatar;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 帐号状态（0正常 1停用）
     */
    @ApiModelProperty("帐号状态（0正常 1停用）")
    private String status;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
    /**
     * 用户角色id
     */
    @ApiModelProperty("用户角色id")
    private String roleId;
}
