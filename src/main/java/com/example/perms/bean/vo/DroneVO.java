package com.example.perms.bean.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Administrator
 * @description
 * @date 2022/9/2 14:39
 */
@Data
public class DroneVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 无人机id
     */
    private Long id;
    /**
     * 型号
     */
    private String model;
    /**
     * 图片路径
     */
    private String modelPhoto;
    /**
     * 序列号
     */
    private String serialNumber;
    /**
     * 保险单号
     */
    private Integer policyNo;
    /**
     * 入网状态（0已入网 1未入网）
     */
    private String networkStatus;
    /**
     * 在线状态（0在线 1离线）
     */
    private String onlineStatus;
    /**
     * 设备状态（0状态好 1状态良 2状态差 3维保中 4已报废）
     */
    private String deviceStatus;
    /**
     * 购买时间
     */
    private LocalDateTime purchasingDate;
    /**
     * 数据信息
     */
    private DroneData droneData;

    /**
     * 数据信息
     */
    private DroneDetails droneDetails;
}
