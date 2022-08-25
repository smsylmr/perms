package com.example.perms.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.perms.bean.entity.SysUser;
import com.example.perms.bean.req.*;
import com.example.perms.bean.vo.SysUserVO;
import com.example.perms.utils.PageUtils;

/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2020-12-09 16:44:03
 */
public interface SysUserService extends IService<SysUser> {

    SysUser selectByName(String name);

    PageUtils<SysUserVO> list(SysUserRequest sysUserRequest);

    void updateState(String userId, UpdStateRequest request);

    void updateUser(SysUserUpdRequest sysUser);

    void saveUser(SysUserUpdRequest sysUser);

    void delete(String userIds);

    void changePwd(String userId, ChangePwdRequest request);

    String login(LoginRequest loginRequest);
}