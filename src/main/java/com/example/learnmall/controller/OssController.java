package com.example.learnmall.controller;

import com.example.learnmall.common.api.CommonResult;
import com.example.learnmall.dto.OssCallbackResult;
import com.example.learnmall.dto.OssPolicyResult;
import com.example.learnmall.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 16:57
 **/
@Api(tags = "OssController",description = "OSS管理")
@Controller
@RequestMapping("/aliyun/oss")
public class OssController {

    @Autowired
    OssService ossService;

    @ApiOperation("oss签名生成")
    @RequestMapping(value = "/policy",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssPolicyResult> policy(){
        return CommonResult.success(ossService.policy());
    }

    @ApiOperation("oss上传成功回调")
    @RequestMapping(value = "callback",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request){
        OssCallbackResult result = ossService.callback(request);
        return CommonResult.success(result);
    }
}
