package com.example.perms.bean.entity;

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
public class DroneMaintenance {
    /**
    * id
    */
    @TableId
    private Long id;
    /**
    * 无人机id
    */
    private String drone_id;
    /**
    * 维保时间
    */
    private LocalDateTime flightTime;
    /**
    * 维保原因
    */
    private String maintenance_reason;


}