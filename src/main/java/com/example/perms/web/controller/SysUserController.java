package com.example.perms.web.controller;

import com.example.perms.bean.req.*;
import com.example.perms.bean.res.ResCode;
import com.example.perms.bean.res.Result;
import com.example.perms.bean.vo.SysUserVO;
import com.example.perms.utils.CurrentUserUtils;
import com.example.perms.utils.PageUtils;
import com.example.perms.web.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author linmr
 * @description:
 * @date 2020/12/14
 */
@Api(tags = "用户模块")
@RestController
public class SysUserController {

    @Resource
    private CurrentUserUtils currentUserUtils;
    @Resource
    private SysUserService sysUserService;

    @PostMapping("/user/login")
    @ApiOperation(value = "登录")
    public Result<String> login(@RequestBody LoginRequest loginRequest){
        String token = sysUserService.login(loginRequest);
        return new Result<>(ResCode.OK,token);
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "退出")
    public Result<Void> logout(){
        currentUserUtils.logout();
        return new Result<>(ResCode.OK);
    }

    @GetMapping("/user/info")
    @ApiOperation(value = "登录用户信息")
    public Result<SysUserVO> getCurrentUser(){
        SysUserVO currentUser = currentUserUtils.getCurrentUser();
        return new Result<>(ResCode.OK,currentUser);
    }

    @PostMapping("/user/list")
    @ApiOperation(value = "用户列表")
    public Result<PageUtils<SysUserVO>> list(@RequestBody SysUserRequest sysUserRequest){
        PageUtils<SysUserVO> list = sysUserService.list(sysUserRequest);
        return new Result<>(ResCode.OK,list);
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户")
    public Result<Void> add(@RequestBody SysUserUpdRequest sysUser){
        sysUserService.saveUser(sysUser);
        return new Result<>(ResCode.OK);
    }

    @GetMapping("/delete/{userId}")
    @ApiOperation(value = "批量删除用户")
    public Result<Void> delete(@PathVariable String userId){
        sysUserService.delete(userId);
        return new Result<>(ResCode.OK);
    }

    @PutMapping("/user/update")
    @ApiOperation(value = "更新用户信息")
    public Result<Void> update(@RequestBody SysUserUpdRequest sysUser){
        sysUserService.updateUser(sysUser);
        return new Result<>(ResCode.OK);
    }

    @PutMapping("/user/state/{userId}")
    @ApiOperation(value = "批量修改用户状态")
    public Result<Void> status(@PathVariable String userId,@RequestBody UpdStateRequest request){
        sysUserService.updateState(userId,request);
        return new Result<>(ResCode.OK);
    }

    @PutMapping("/user/pwd/{userId}")
    @ApiOperation(value = "修改密码")
    public Result<Void> changePwd(@PathVariable String userId,@RequestBody ChangePwdRequest request){
        sysUserService.changePwd(userId,request);
        return new Result<>(ResCode.OK);
    }
}
