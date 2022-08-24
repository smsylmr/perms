package com.example.perms.web.controller;

import com.example.perms.bean.entity.SysUser;
import com.example.perms.web.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
public class TestController {
    @Resource
    private SysUserService sysUserService;

    @GetMapping("/dss")
    public String ss(){
        return "tsdgsdhjdhd";
    }

    @GetMapping("/test")
    public void test(){
        List<SysUser> list = sysUserService.list();
        System.out.println(list.toString());
    }
}
