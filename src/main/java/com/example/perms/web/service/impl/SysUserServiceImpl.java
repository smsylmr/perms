package com.example.perms.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.SysUser;
import com.example.perms.bean.req.SysUserRequest;
import com.example.perms.bean.req.UpdStateRequest;
import com.example.perms.bean.vo.SysUserVO;
import com.example.perms.utils.CurrentUserUtils;
import com.example.perms.utils.OrikaUtils;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.mapper.SysUserMapper;
import com.example.perms.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:44:03
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Override
    public SysUser selectByName(String name) {
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",name);
        System.out.println(this.baseMapper.selectOne(queryWrapper));
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public PageUtils<SysUserVO> list(SysUserRequest sysUserRequest) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(sysUserRequest.getUserName())){
            queryWrapper.like("user_name",sysUserRequest.getUserName());
        }
        if(StrUtil.isNotEmpty(sysUserRequest.getDeptId())){
            queryWrapper.eq("dept_id",sysUserRequest.getDeptId());
        }
        if(StrUtil.isNotEmpty(sysUserRequest.getEmail())){
            queryWrapper.like("email",sysUserRequest.getEmail());
        }
        if(StrUtil.isNotEmpty(sysUserRequest.getPhoneNumber())){
            queryWrapper.like("phone_number",sysUserRequest.getPhoneNumber());
        }
        Page<SysUser> page = page(new Page<>(sysUserRequest.getCurPage(), sysUserRequest.getLimit()), queryWrapper);
        List<SysUserVO> sysUserVOS = OrikaUtils.mapAsList(page.getRecords(), SysUserVO.class);
        sysUserVOS.forEach(sysUserVO -> {
            sysUserVO.setEnable(sysUserVO.getStatus().equals("0"));
        });
        return new PageUtils<>(sysUserVOS,page);

    }

    @Override
    public void updateState(String userIds, UpdStateRequest request) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        String[] split = userIds.split(",");
        queryWrapper.in("user_id", Arrays.asList(split));
        List<SysUser> list = list(queryWrapper);
        list.forEach(sysUser -> {
            sysUser.setStatus(request.getEnable().equals("true")?"0":"1");
            sysUser.setUpdateTime(LocalDateTime.now());
            sysUser.setUpdateBy(currentUserUtils.getCurrentUser().getUserId().toString());
        });
        updateBatchById(list);
    }

    @Override
    public void updateUser(SysUser sysUser) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bCryptPasswordEncoder.encode(sysUser.getPassword());
        SysUser byId = getById(sysUser.getUserId());
        if(!byId.getPassword().equals(pwd)){

        }
        sysUser.setPassword(pwd);
        updateById(sysUser);
    }
}