package com.example.perms.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.DroneFlight;
import com.example.perms.bean.entity.DroneMaintenance;
import com.example.perms.bean.vo.DroneData;
import com.example.perms.web.mapper.DroneFlightMapper;
import com.example.perms.web.service.DroneFlightService;
import com.example.perms.web.service.DroneMaintenanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 无人机飞行表(DroneFlight)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:21
 */
@Service("droneFlightService")
public class DroneFlightServiceImpl extends ServiceImpl<DroneFlightMapper, DroneFlight> implements DroneFlightService {

    @Resource
    private DroneMaintenanceService droneMaintenanceService;

    @Override
    public DroneData getFlightData(String droneId) {
        DroneData flightData = this.getBaseMapper().getFlightData(droneId);
        DroneMaintenance last = droneMaintenanceService.getLastMaintenanceByDroneId(droneId);
        DroneData dataAfterLastMaintenance = this.getBaseMapper().getFlightDataAfterLastMaintenance(droneId, last.getMaintenanceTime());
        flightData.setAfterLastMaintenanceFlightMileage(dataAfterLastMaintenance.getAfterLastMaintenanceFlightMileage());
        flightData.setAfterLastMaintenanceFlightTime(dataAfterLastMaintenance.getAfterLastMaintenanceFlightTime());
        Integer countByDroneId = droneMaintenanceService.getCountByDroneId(droneId);
        flightData.setMaintenanceSorties(countByDroneId);
        DroneFlight flight = this.getBaseMapper().getLastFlightByDroneId(droneId);
        flightData.setLastOnlineTime(flight.getLastOnlineTime());
        return flightData;
    }
}