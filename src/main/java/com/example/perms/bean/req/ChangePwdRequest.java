package com.example.perms.bean.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author linmr
 * @description:
 * @date 2022/8/24
 */
@Data
public class ChangePwdRequest {
    @ApiModelProperty("旧密码")
    private String oldPwd;

    @ApiModelProperty("新密码")
    private String newPwd;
}
