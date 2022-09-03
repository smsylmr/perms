package com.example.perms.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 无人机维保记录表(DroneMaintenanceRecords)实体类
 *
 * @author makejava
 * @since 2020-12-09 16:39:46
 */
@Data
public class DroneFlight {
    /**
    * id
    */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
    * 无人机id
    */
    private String droneId;
    /**
     * 本次飞行里程
     */
    private String flightMileage;
    /**
     * 本次飞行耗时
     */
    private String flightConsume;
    /**
     * 飞行时间
     */
    private LocalDateTime flightTime;
    /**
    * 最后在线时间
    */
    private LocalDateTime lastOnlineTime;



}