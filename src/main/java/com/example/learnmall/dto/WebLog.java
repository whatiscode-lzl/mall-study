package com.example.learnmall.dto;

import lombok.Data;

/**
 * @Author liaozhenglong
 * @Date 2019/11/25 9:09
 *   日志封装类，(controller层的日志封装)
 **/
@Data
public class WebLog {
    /**
     * 操作描述*/
    private String description;

    /**
     * 操作用户*/
    private String username;

    /**
     * 操作时间*/
    private String startTime;

    /**
     * 消耗时间*/
    private Integer spendTimen;

    /**
     *根路径 */
    private String basePath;

    /**
     * URI*/
    private String uri;

    /**
     * URL*/
    private String url;

    /**
     * 请求类型*/
    private String method;

    /**
     * ip地址*/
    private String ip;

    /**
     * 请求参数*/
    private Object parameter;

    /**
     * 请求返回的结果*/
    private Object result;
}
