package com.example.learnmall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author liaozhenglong
 * @Date 2019/11/21 14:23
 * 订单超时间取消并解锁库存的定时器
 **/
@Component
public class OrderTimeOutCancelTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每1分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
     */
    @Scheduled(cron = "0 0/1 * ? * ?")
    private void cancelOrderOutTimeTask(){
        System.out.println("定时任务之执行成功");
        LOGGER.info("取消订单，并根据订单解锁库存");
    }
}
