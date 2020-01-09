package com.example.learnmall.component;

import com.example.learnmall.dto.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 14:18
 *   取消订单的消息的发出者
 *   用于向订单延迟消息队列（mall.order.cancel.ttl）里发送消息
 **/
@Component
public class CancelOrderSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId,final Long delayTimes){
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouterKey(),
                orderId, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //给消息设置延迟毫秒值
                        message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                        return message;
                    }
                });
        LOGGER.info("send delay message orderId:{}",orderId);
    }
}
