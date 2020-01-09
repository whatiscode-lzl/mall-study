package com.example.learnmall.service.impl;

import com.example.learnmall.common.api.CommonResult;
import com.example.learnmall.component.CancelOrderSender;
import com.example.learnmall.dto.OrderParam;
import com.example.learnmall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 14:40
 **/
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);
    @Autowired
    CancelOrderSender cancelOrderSender;
    @Override
    public void cancelOrder(Long orderId) {
        //执行一系列取消订单操作
        LOGGER.info("取消某个订单号为：{}"+orderId);
    }

    @Override
    public CommonResult generatoOrder(OrderParam orderParam) {
        //执行一系列下单操作
        LOGGER.info("执行下单操作");
        //下单完成后开启一个延迟取消订单的消息
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null,"下单成功");
    }

    private void sendDelayMessageCancelOrder(long orderId) {

        //设置订单超时时间
        long delayTimes = 2*1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId,delayTimes);
    }
}
