package com.example.perms.web.controller;

import com.example.perms.bean.entity.SysDept;
import com.example.perms.bean.req.SysDeptRequest;
import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import com.example.perms.bean.vo.SysDeptTreeVO;
import com.example.perms.bean.vo.SysDeptVO;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */

@RestController
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;


    @PostMapping("/dept/list")
    public Result<PageUtils<SysDeptVO>> list(@RequestBody SysDeptRequest sysDeptRequest){
        PageUtils<SysDeptVO> list = sysDeptService.list(sysDeptRequest);
        return new Result<>(ResCode.OK,list);
    }

    @GetMapping("/dept/select")
    public Result<List<SysDeptTreeVO>> select(){
        List<SysDeptTreeVO> treeVOS = sysDeptService.select();
        return new Result<>(ResCode.OK,treeVOS);
    }

    @PostMapping("/dept")
    public Result<Void> add(@RequestBody SysDept sysDept){
        sysDeptService.save(sysDept);
        return new Result<>(ResCode.OK);
    }

    @DeleteMapping("/dept/{deptId}")
    public Result<Void> delete(@PathVariable String deptId){
        sysDeptService.removeById(deptId);
        return new Result<>(ResCode.OK);
    }

    @PutMapping("/dept/{deptId}")
    public Result<Void> update(@RequestBody SysDept sysDept){
        sysDeptService.updateById(sysDept);
        return new Result<>(ResCode.OK);
    }

}
