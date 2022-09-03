package com.example.perms.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
    * 无人机id
    */
    private String droneId;
    /**
    * 维保时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime maintenanceTime;
    /**
    * 维保原因
    */
    private String maintenanceReason;


}