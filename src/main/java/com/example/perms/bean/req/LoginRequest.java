package com.example.perms.bean.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author linmr
 * @description:
 * @date 2022/8/24
 */
@Data
public class LoginRequest {
    @ApiModelProperty("用户名")
    private String loginName;
    @ApiModelProperty("密码")
    private String password;
}
