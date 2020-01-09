package com.example.learnmall.dto;

/**
 * @Author liaozhenglong
 * @Date 2019/11/21 10:48
 **/

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登入用户参数*/
public class UmsAdminLoginParam {

    @ApiModelProperty(value = "用户名",required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "登入密码",required = true)
    @NotEmpty(message = "登入密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
