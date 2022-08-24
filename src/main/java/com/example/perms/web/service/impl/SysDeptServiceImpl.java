package com.example.perms.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.perms.bean.entity.SysDept;
import com.example.perms.bean.req.SysDeptRequest;
import com.example.perms.bean.vo.SysDeptTreeVO;
import com.example.perms.bean.vo.SysDeptVO;
import com.example.perms.utils.OrikaUtils;
import com.example.perms.utils.PageUtils;
import com.example.perms.utils.TreeUtils;
import com.example.perms.web.mapper.SysDeptMapper;
import com.example.perms.web.service.SysDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门表(SysDept)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 16:42:21
 */
@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper,SysDept> implements SysDeptService {

    @Override
    public PageUtils<SysDeptVO> list(SysDeptRequest sysDeptRequest) {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(sysDeptRequest.getDeptId())){
            queryWrapper.eq("dept_id",sysDeptRequest.getDeptId());
        }
        Page<SysDept> page = page(new Page<>(sysDeptRequest.getCurPage(), sysDeptRequest.getLimit()), queryWrapper);
        List<SysDeptVO> sysDeptVOS = OrikaUtils.mapAsList(page.getRecords(), SysDeptVO.class);
        return new PageUtils<>(sysDeptVOS,page);
    }

    @Override
    public List<SysDeptTreeVO> select() {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag","0");
        List<SysDept> list = list(queryWrapper);
        List<SysDeptTreeVO> temp = new ArrayList<>();
        list.forEach(sysDept -> {
            SysDeptTreeVO sysDeptTreeVO = new SysDeptTreeVO();
            BeanUtils.copyProperties(sysDept, sysDeptTreeVO);
            temp.add(sysDeptTreeVO);
        });
        return TreeUtils.generateTrees(temp);
    }
}