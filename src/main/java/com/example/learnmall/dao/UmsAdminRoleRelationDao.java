package com.example.learnmall.dao;

import com.example.learnmall.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/20 17:00
 * 后台用户与角色管理自定dao
 **/
public interface UmsAdminRoleRelationDao {
    /**
     * 获取用户所有的权限*/
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);//getPermissionList
}
