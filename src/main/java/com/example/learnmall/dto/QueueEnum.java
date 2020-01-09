package com.example.learnmall.dto;

import lombok.Getter;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 10:33
 * 消息队列配置枚举类
 * 用于延迟消息队列及用于取消订单消息队列的常量定义，包括交换机名称，队列名称路由键名称
 **/
@Getter
public enum QueueEnum {

    /**
     * 消息通知队列*/
    QUEUE_ORDER_CANCEL("mall.order.direct","mall.order.cancel","mall.order.cancel"),

    /**
     * 消息通知ttl队列*/
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl","mall.order.cancel.ttl","mall.order.cancel.ttl");

    /**
     * 交换名称*/
    private String exchange;

    /**
     * 队列名称*/
    private String name;

    /**
     * 路由键*/
    private String routerKey;

    QueueEnum(String exchange,String name,String routerKey){
        this.exchange=exchange;
        this.name=name;
        this.routerKey=routerKey;
    }
}
