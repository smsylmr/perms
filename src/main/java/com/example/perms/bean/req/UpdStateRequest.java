package com.example.perms.bean.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linmr
 * @description:
 * @date 2020/12/25
 */
@Data
public class UpdStateRequest implements Serializable {
    private String enable;
}
