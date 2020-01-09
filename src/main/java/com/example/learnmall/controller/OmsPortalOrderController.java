package com.example.learnmall.controller;

import com.example.learnmall.common.api.CommonResult;
import com.example.learnmall.dto.OrderParam;
import com.example.learnmall.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 14:54
 **/
@Api(tags = "OmsPortalOrderController ",description = "订单管理")
@Controller
@RequestMapping("/order")
public class OmsPortalOrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderController.class);

    @Autowired
    OmsPortalOrderService portalOrderService;

    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "generateOrder",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult generateOrder(@RequestBody OrderParam orderParam){
        return  portalOrderService.generatoOrder(orderParam);
    }
}
