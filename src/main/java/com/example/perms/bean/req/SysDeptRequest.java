package com.example.perms.bean.req;

import lombok.Data;

/**
 * @author linmr
 * @description:
 * @date 2020/12/22
 */
@Data
public class SysDeptRequest extends BasePageRequest{


    /**
     * 部门ID
     */
    private String deptId;

}
