package com.example.learnmall.service;

import com.example.learnmall.mbg.model.UmsAdmin;
import com.example.learnmall.mbg.model.UmsPermission;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/20 16:46
 * 后台管理员的service
 *
 **/
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     * */
    UmsAdmin getAdminByUserName(String username);

    /**
     * 注册功能
     * */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登入功能
     * @param username 用户名
     * @param  password 用户密码
     * @return 生成的JWT的token*/
    String login(String username,String password);

    /**
     * 获取用户所有的权限包括+-权限*/
    List<UmsPermission> getPermissionList(Long id);
}
