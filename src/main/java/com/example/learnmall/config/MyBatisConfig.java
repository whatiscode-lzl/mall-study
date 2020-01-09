package com.example.learnmall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author liaozhenglong
 * @Date 2019/11/19 16:06
 * Mybatis的配置类
 * 用于配置需要动态生成的mapper接口的路径
 **/
@Configuration
@MapperScan({"com.example.learnmall.mbg.mapper","com.example.learnmall.dao"})
public class MyBatisConfig {
}
