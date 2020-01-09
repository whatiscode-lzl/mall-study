package com.example.learnmall.service.impl;

import com.example.learnmall.common.api.CommonResult;
import com.example.learnmall.service.RedisService;
import com.example.learnmall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @Author liaozhenglong
 * @Date 2019/11/20 14:53
 * 会员管理实现类
 **/
@Service
public class UmsMemberServiceImpl implements UmsMemberService {


    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long REDIS_KEY_EXPIRE_AUTH_CODE;



    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        //验证码绑定到手机号，并存储到Redis中
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,REDIS_KEY_EXPIRE_AUTH_CODE);
        return CommonResult.success(sb.toString());
    }

    //对输入的验证码进行校验
    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)){
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+telephone);
        boolean result = authCode.endsWith(realAuthCode);
        if (result){
            return CommonResult.success("验证码校验成功");
        }
        return CommonResult.failed("验证码校验失败");
    }
}
