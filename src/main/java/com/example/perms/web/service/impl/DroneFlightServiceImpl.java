package com.example.perms.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.Drone;
import com.example.perms.bean.entity.DroneFlight;
import com.example.perms.bean.entity.DroneMaintenance;
import com.example.perms.bean.req.DroneFlightRequest;
import com.example.perms.bean.vo.DroneData;
import com.example.perms.bean.vo.DroneFlightVO;
import com.example.perms.bean.vo.DroneVO;
import com.example.perms.utils.OrikaUtils;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.mapper.DroneFlightMapper;
import com.example.perms.web.service.DroneFlightService;
import com.example.perms.web.service.DroneMaintenanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        if(last==null){
            flightData.setAfterLastMaintenanceFlightTime(flightData.getTotalFlightTime());
            flightData.setAfterLastMaintenanceFlightMileage(flightData.getTotalFlightMileage());
        }else{
            DroneData dataAfterLastMaintenance = this.getBaseMapper().getFlightDataAfterLastMaintenance(droneId, last.getMaintenanceTime());
            flightData.setAfterLastMaintenanceFlightMileage(dataAfterLastMaintenance.getAfterLastMaintenanceFlightMileage());
            flightData.setAfterLastMaintenanceFlightTime(dataAfterLastMaintenance.getAfterLastMaintenanceFlightTime());
        }
        Integer countByDroneId = droneMaintenanceService.getCountByDroneId(droneId);
        flightData.setMaintenanceSorties(countByDroneId);
        DroneFlight flight = this.getBaseMapper().getLastFlightByDroneId(droneId);
        if(flight!=null){
            flightData.setLastOnlineTime(flight.getLastOnlineTime());
        }
        return flightData;
    }

    @Override
    public PageUtils<DroneFlightVO> list(DroneFlightRequest droneFlightRequest) {
        QueryWrapper<DroneFlight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("drone_id",droneFlightRequest.getDroneId());
        Page<DroneFlight> page = page(new Page<>(droneFlightRequest.getCurPage(), droneFlightRequest.getLimit()), queryWrapper);
        List<DroneFlightVO> sysDeptVOS = OrikaUtils.mapAsList(page.getRecords(), DroneFlightVO.class);
        return new PageUtils<>(sysDeptVOS,page);
    }
}