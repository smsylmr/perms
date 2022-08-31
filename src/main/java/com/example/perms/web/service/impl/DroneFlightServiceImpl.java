package com.example.perms.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.DroneFlight;
import com.example.perms.bean.entity.DroneMaintenance;
import com.example.perms.web.mapper.DroneFlightMapper;
import com.example.perms.web.mapper.DroneMaintenanceMapper;
import com.example.perms.web.service.DroneFlightService;
import com.example.perms.web.service.DroneMaintenanceService;
import org.springframework.stereotype.Service;

/**
 * 无人机飞行表(DroneFlight)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:21
 */
@Service("sysDeptService")
public class DroneFlightServiceImpl extends ServiceImpl<DroneFlightMapper, DroneFlight> implements DroneFlightService {


}