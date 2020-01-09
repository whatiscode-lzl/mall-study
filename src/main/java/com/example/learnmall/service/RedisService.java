package com.example.learnmall.service;

/**
 * @Author liaozhenglong
 * @Date 2019/11/20 14:23
 * redis操作Service
 * 对象和数组都已json的形式进行存储
 **/
public interface RedisService {

    /**
     * 存储数据*/
    void set(String key,String value);

    /**
     * 获取数据*/
    String get(String key);

    /**
     * 设置超期时间*/
    boolean expire(String key,long expire);

    /**
     * 删除数据*/
    void remove(String key);

    /**
     * 自增操作*/
    Long increment(String key,long delta);
}
