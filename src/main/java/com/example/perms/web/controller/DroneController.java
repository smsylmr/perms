package com.example.perms.web.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.perms.bean.entity.Drone;
import com.example.perms.bean.req.DroneAdd;
import com.example.perms.bean.req.DroneRequest;
import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import com.example.perms.bean.vo.DroneData;
import com.example.perms.bean.vo.DroneVO;
import com.example.perms.utils.OrikaUtils;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.service.DroneFlightService;
import com.example.perms.web.service.DroneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */

@Api(tags = "无人机基本信息模块")
@RestController
@RequestMapping("/drone")
public class DroneController {

    @Resource
    private DroneService droneService;
    @Resource
    private DroneFlightService droneFlightService;


    @PostMapping("/list")
    @ApiOperation(value = "列表")
    public Result<PageUtils<DroneVO>> list(@RequestBody DroneRequest droneRequest){
        PageUtils<DroneVO> list = droneService.list(droneRequest);
        return new Result<>(ResCode.OK,list);
    }

    @GetMapping("/getFlightData/{droneId}")
    @ApiOperation(value = "详情")
    public Result<DroneData> getFlightData(@PathVariable String droneId){
        DroneData droneData = droneFlightService.getFlightData(droneId);
        return new Result<>(ResCode.OK,droneData);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public Result<Void> add(@RequestBody DroneAdd drone){
        Drone drone1 = OrikaUtils.map(drone, Drone.class);
        droneService.save(drone1);
        return new Result<>(ResCode.OK);
    }

    @DeleteMapping("/del/{droneId}")
    @ApiOperation(value = "删除")
    public Result<Void> delete(@PathVariable String droneId){
        droneService.del(droneId);
        return new Result<>(ResCode.OK);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新")
    public Result<Void> update(@RequestBody DroneAdd drone){
        Drone drone1 = OrikaUtils.map(drone, Drone.class);
        droneService.updateById(drone1);
        return new Result<>(ResCode.OK);
    }

}
