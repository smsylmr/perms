package com.example.perms.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.Drone;
import com.example.perms.bean.req.DroneRequest;
import com.example.perms.bean.vo.DroneVO;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.mapper.DroneMapper;
import com.example.perms.web.service.DroneService;
import org.springframework.stereotype.Service;

/**
 * 无人机表(Drone)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:21
 */
@Service("sysDeptService")
public class DroneServiceImpl extends ServiceImpl<DroneMapper, Drone> implements DroneService {


    @Override
    public PageUtils<DroneVO> list(DroneRequest droneRequest) {
        return null;
    }
}