package com.example.perms.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.perms.bean.entity.SysDept;
import com.example.perms.bean.req.SysDeptRequest;
import com.example.perms.bean.vo.SysDeptTreeVO;
import com.example.perms.bean.vo.SysDeptVO;
import com.example.perms.utils.PageUtils;

import java.util.List;

/**
 * 部门表(SysDept)表服务接口
 *
 * @author makejava
 * @since 2020-12-09 16:42:18
 */
public interface SysDeptService extends IService<SysDept> {

    PageUtils<SysDeptVO> list(SysDeptRequest sysDeptRequest);

    List<SysDeptTreeVO> select();
}