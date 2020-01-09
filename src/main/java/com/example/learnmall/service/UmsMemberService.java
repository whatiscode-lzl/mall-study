package com.example.learnmall.service;

import com.example.learnmall.common.api.CommonResult;

/**
 * @Author liaozhenglong
 * @Date 2019/11/20 14:47
 * 会员管理
 **/
public interface UmsMemberService {
    /**
     * 生成验证码*/
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号是否匹配*/

    CommonResult verifyAuthCode(String telephone,String authCode);
}
