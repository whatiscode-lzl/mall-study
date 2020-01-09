package com.example.learnmall.component;

import com.example.learnmall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 14:29
 *   取消订单的处理者
 **/
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    OmsPortalOrderService omsPortalOrderService;

    @RabbitHandler
    public void handle(Long orderId){
        LOGGER.info("收到延迟消息的订单id:{}"+orderId);
        omsPortalOrderService.cancelOrder(orderId);
    }


}
