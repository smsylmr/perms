package com.example.perms.web.controller;

import com.example.perms.bean.entity.SysUser;
import com.example.perms.bean.req.SysUserRequest;
import com.example.perms.bean.req.UpdStateRequest;
import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import com.example.perms.bean.vo.SysUserVO;
import com.example.perms.utils.CurrentUserUtils;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */

@RestController
public class SysUserController {

    @Autowired
    private CurrentUserUtils currentUserUtils;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user/logout")
    public Result<Void> logout(){
        currentUserUtils.logout();
        return new Result<>(ResCode.OK);
    }

    @GetMapping("/user/info")
    public Result<SysUserVO> getCurrentUser(){
        SysUserVO currentUser = currentUserUtils.getCurrentUser();
        return new Result<>(ResCode.OK,currentUser);
    }

    @PostMapping("/user/list")
    public Result<PageUtils<SysUserVO>> list(@RequestBody SysUserRequest sysUserRequest){
        PageUtils<SysUserVO> list = sysUserService.list(sysUserRequest);
        return new Result<>(ResCode.OK,list);
    }

    @PostMapping("/user")
    public Result<Void> add(@RequestBody SysUser sysUser){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        sysUserService.save(sysUser);
        return new Result<>(ResCode.OK);
    }

//    @GetMapping("/delete/{userId}")
//    public Result<Void> delete(@PathVariable String userId){
//        sysUserService.removeById(userId);
//        return new Result<>(ResCode.OK);
//    }

    @PutMapping("/user/{userId}")
    public Result<Void> update(@RequestBody SysUser sysUser){

        sysUserService.updateUser(sysUser);
        return new Result<>(ResCode.OK);
    }

    @PutMapping("/user/state/{userId}")
    public Result<Void> status(@PathVariable String userId,@RequestBody UpdStateRequest request){
        sysUserService.updateState(userId,request);
        return new Result<>(ResCode.OK);
    }
}
