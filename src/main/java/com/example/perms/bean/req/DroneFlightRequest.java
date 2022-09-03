package com.example.perms.bean.req;

import lombok.Data;

/**
 * @author linmr
 * @description:
 * @date 2022/9/3
 */
@Data
public class DroneFlightRequest extends BasePageRequest {
    /**
     * 无人机id
     */
    private String droneId;
}
