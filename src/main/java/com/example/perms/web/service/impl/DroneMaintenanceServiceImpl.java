package com.example.perms.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.DroneFlight;
import com.example.perms.bean.entity.DroneMaintenance;
import com.example.perms.bean.req.DroneMaintenanceRequest;
import com.example.perms.bean.vo.DroneFlightVO;
import com.example.perms.bean.vo.DroneMaintenanceVO;
import com.example.perms.utils.OrikaUtils;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.mapper.DroneMaintenanceMapper;
import com.example.perms.web.service.DroneMaintenanceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 无人机表(Drone)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:21
 */
@Service("droneMaintenanceService")
public class DroneMaintenanceServiceImpl extends ServiceImpl<DroneMaintenanceMapper, DroneMaintenance> implements DroneMaintenanceService {


    @Override
    public DroneMaintenance getLastMaintenanceByDroneId(String droneId) {
        return this.getBaseMapper().getLastMaintenanceByDroneId(droneId);
    }

    @Override
    public Integer getCountByDroneId(String droneId) {
        return this.getBaseMapper().getCountByDroneId(droneId);
    }

    @Override
    public PageUtils<DroneMaintenanceVO> list(DroneMaintenanceRequest droneMaintenanceRequest) {
        QueryWrapper<DroneMaintenance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("drone_id",droneMaintenanceRequest.getDroneId());
        Page<DroneMaintenance> page = page(new Page<>(droneMaintenanceRequest.getCurPage(), droneMaintenanceRequest.getLimit()), queryWrapper);
        List<DroneMaintenanceVO> sysDeptVOS = OrikaUtils.mapAsList(page.getRecords(), DroneMaintenanceVO.class);
        return new PageUtils<>(sysDeptVOS,page);
    }
}