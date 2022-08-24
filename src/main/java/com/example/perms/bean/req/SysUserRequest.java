package com.example.perms.bean.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linmr
 * @description:
 * @date 2020/12/22
 */
@Data
public class SysUserRequest extends BasePageRequest implements Serializable {

    private String userId;

    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 登录账号
     */
    private String loginName;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phoneNumber;
}
