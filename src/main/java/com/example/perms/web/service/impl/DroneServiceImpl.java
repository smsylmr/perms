package com.example.perms.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.Drone;
import com.example.perms.bean.req.DroneRequest;
import com.example.perms.bean.vo.DroneVO;
import com.example.perms.utils.OrikaUtils;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.mapper.DroneMapper;
import com.example.perms.web.service.DroneService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 无人机表(Drone)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:21
 */
@Service("droneService")
public class DroneServiceImpl extends ServiceImpl<DroneMapper, Drone> implements DroneService {


    @Override
    public PageUtils<DroneVO> list(DroneRequest droneRequest) {
        QueryWrapper<Drone> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(droneRequest.getModel())){
            queryWrapper.eq("model",droneRequest.getModel());
        }
        if(StrUtil.isNotEmpty(droneRequest.getSerialNumber())){
            queryWrapper.eq("serial_number",droneRequest.getSerialNumber());
        }
        if(StrUtil.isNotEmpty(droneRequest.getDeviceStatus())){
            queryWrapper.eq("device_status",Integer.parseInt(droneRequest.getDeviceStatus()));
        }
        if(StrUtil.isNotEmpty(droneRequest.getNetworkStatus())){
            queryWrapper.eq("network_status",Integer.parseInt(droneRequest.getNetworkStatus()));
        }
        if(StrUtil.isNotEmpty(droneRequest.getOnlineStatus())){
            queryWrapper.eq("online_status",Integer.parseInt(droneRequest.getOnlineStatus()));
        }
        queryWrapper.eq("del_flag",0);
        Page<Drone> page = page(new Page<>(droneRequest.getCurPage(), droneRequest.getLimit()), queryWrapper);
        List<DroneVO> sysDeptVOS = OrikaUtils.mapAsList(page.getRecords(), DroneVO.class);
        return new PageUtils<>(sysDeptVOS,page);
    }

    @Override
    public void del(String droneId) {
        Drone byId = this.getById(droneId);
        byId.setDelFlag(1);
        this.updateById(byId);
    }

}