package com.example.perms.web.controller;

import com.example.perms.bean.entity.Drone;
import com.example.perms.bean.req.DroneRequest;
import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import com.example.perms.bean.vo.DroneVO;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.service.DroneService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */

@RestController
public class DroneController {

    @Resource
    private DroneService droneService;


    @PostMapping("/drone/list")
    public Result<PageUtils<DroneVO>> list(@RequestBody DroneRequest droneRequest){
        PageUtils<DroneVO> list = droneService.list(droneRequest);
        return new Result<>(ResCode.OK,list);
    }


    @PostMapping("/drone/add")
    public Result<Void> add(@RequestBody Drone drone){
        droneService.save(drone);
        return new Result<>(ResCode.OK);
    }

    @DeleteMapping("/drone/{droneId}")
    public Result<Void> delete(@PathVariable String droneId){
        droneService.removeById(droneId);
        return new Result<>(ResCode.OK);
    }

    @PutMapping("/drone/update")
    public Result<Void> update(@RequestBody Drone drone){
        droneService.updateById(drone);
        return new Result<>(ResCode.OK);
    }

}
