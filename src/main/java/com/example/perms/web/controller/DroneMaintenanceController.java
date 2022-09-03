package com.example.perms.web.controller;

import com.example.perms.bean.entity.DroneMaintenance;
import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import com.example.perms.web.service.DroneMaintenanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */

@Api(tags = "无人机维保记录模块")
@RestController
@RequestMapping("/maintenance")
public class DroneMaintenanceController {
    @Resource
    private DroneMaintenanceService droneMaintenanceService;

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public Result<Void> add(@RequestBody DroneMaintenance droneMaintenance){
        droneMaintenanceService.save(droneMaintenance);
        return new Result<>(ResCode.OK);
    }
}
