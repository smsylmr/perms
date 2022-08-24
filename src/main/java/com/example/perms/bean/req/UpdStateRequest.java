package com.example.perms.bean.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author linmr
 * @description:
 * @date 2020/12/25
 */
@Data
public class UpdStateRequest {
    @ApiModelProperty("禁用 是：true，否：false")
    private String enable;
}
