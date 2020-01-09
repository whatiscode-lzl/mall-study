package com.example.learnmall.controller;

import com.example.learnmall.common.api.CommonResult;
import com.example.learnmall.nosql.mongodb.document.MemberReadHistory;
import com.example.learnmall.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 9:07
 * 会员商品浏览记录管理
 **/
@Api(tags = "MemberReadHistoryController ",description = "会员商品浏览记录管理")
@Controller
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberReadHistoryController .class);

    @Autowired
    MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory){
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0){
            return CommonResult.success(memberReadHistory);
        }
        return CommonResult.failed("保存浏览记录失败");
    }

    @ApiOperation("删除浏览记录")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids")List<String> ids){
        int count = memberReadHistoryService.delete(ids);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed("删除浏览记录失败");
    }

    @ApiOperation("获取浏览记录")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<MemberReadHistory>> list(Long memberId){
        List<MemberReadHistory> list = memberReadHistoryService.list(memberId);
        return CommonResult.success(list);
    }
}
