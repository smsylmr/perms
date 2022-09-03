package com.example.perms.web.controller;

import com.example.perms.bean.entity.Drone;
import com.example.perms.bean.entity.DroneFlight;
import com.example.perms.bean.entity.SysDept;
import com.example.perms.bean.req.*;
import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import com.example.perms.bean.vo.DroneFlightVO;
import com.example.perms.bean.vo.DroneVO;
import com.example.perms.bean.vo.SysDeptTreeVO;
import com.example.perms.bean.vo.SysDeptVO;
import com.example.perms.utils.OrikaUtils;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.service.DroneFlightService;
import com.example.perms.web.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */

@Api(tags = "无人机飞行记录模块")
@RestController
@RequestMapping("/flight")
public class DroneFlightController {

    @Resource
    private DroneFlightService droneFlightService;

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public Result<Void> add(@RequestBody DroneFlightAdd droneFlightAdd){
        DroneFlight drone1 = OrikaUtils.map(droneFlightAdd, DroneFlight.class);
        droneFlightService.save(drone1);
        return new Result<>(ResCode.OK);
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表")
    public Result<PageUtils<DroneFlightVO>> list(@RequestBody DroneFlightRequest droneFlightRequest){
        PageUtils<DroneFlightVO> list = droneFlightService.list(droneFlightRequest);
        return new Result<>(ResCode.OK,list);
    }
}
