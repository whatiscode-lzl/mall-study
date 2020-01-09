package com.example.learnmall.config;

import com.example.learnmall.dto.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 10:45
 * 消息队列配置
 *  用于配置交换机、队列及队列和交换机之间绑定关系
 **/
@Configuration
public class RabbitMqConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConfig.class);

    /**
     * 订单消息实际队列绑定的交换机*/
    @Bean
    DirectExchange orderDirect(){
        LOGGER.info("创建交换机:"+QueueEnum.QUEUE_ORDER_CANCEL.getExchange());
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单延迟队列绑定的交换机*/
    @Bean
    DirectExchange orderTtlDirect(){
        LOGGER.info("创建延时交换机:"+QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange());
        return (DirectExchange)ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单实际消费队列*/
    @Bean
    Queue orderQueue(){
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }

    /**
     * 订单延迟队列（死信队列)*/
    @Bean
    Queue orderTtlQueue(){
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
                .withArgument("x-dead-letter-exchange",QueueEnum.QUEUE_ORDER_CANCEL.getExchange())//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key",QueueEnum.QUEUE_ORDER_CANCEL.getRouterKey())//到期后转发的路由键
                .build();
    }

    /**
     * 将订单队列绑定到交换机*/
    @Bean
    Binding orderBinding(DirectExchange orderDirect,Queue orderQueue){
        return BindingBuilder
                .bind(orderQueue)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouterKey());
    }

    /**
     * 将订单延迟队列绑定到交换机*/
    @Bean
    Binding orderTtlBinding(DirectExchange orderTtlDirect,Queue orderTtlQueue){
        return BindingBuilder
                .bind(orderTtlQueue)
                .to(orderTtlDirect)
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouterKey());
    }
}
