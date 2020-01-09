package com.example.learnmall.service;

import com.example.learnmall.common.api.CommonResult;
import com.example.learnmall.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 14:32
 *   前台订单的管理
 **/
public interface OmsPortalOrderService {

    /**
     * 取消单个超时订单*/
    @Transactional
    void cancelOrder(Long orderId);

    /**
     * 根据提交信息生成订单*/
    @Transactional
    CommonResult generatoOrder(OrderParam orderParam);


}
