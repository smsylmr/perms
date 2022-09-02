package com.example.perms.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.perms.bean.entity.Drone;
import com.example.perms.bean.req.DroneRequest;
import com.example.perms.bean.vo.DroneVO;
import com.example.perms.utils.PageUtils;

/**
 * 无人机表(Drone)表服务接口
 *
 * @author makejava
 * @since 2020-12-09 16:42:18
 */
public interface DroneService extends IService<Drone> {

    PageUtils<DroneVO> list(DroneRequest droneRequest);
}