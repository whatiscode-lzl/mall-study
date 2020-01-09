package com.example.learnmall.controller;

import com.example.learnmall.common.api.CommonPage;
import com.example.learnmall.common.api.CommonResult;
import com.example.learnmall.mbg.model.PmsBrand;
import com.example.learnmall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/20 9:40
 * 品牌管理
 **/

@Api(tags = "PmsBrandController",description = "品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    PmsBrandService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation("获取所有的品牌列表")
    @RequestMapping(value = "listAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList(){
        return CommonResult.success(service.listAllBrands());
    }

    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand){
        CommonResult commonResult;
        int count = service.createBrand(pmsBrand);
        if (count==1){
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}",pmsBrand);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}",pmsBrand);
        }
        return  commonResult;
    }

    @ApiOperation("更新指定id品牌的信息")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand,
                                    BindingResult result){
        CommonResult commonResult=null;
        int count = service.updateBrand(id,pmsBrand);
        if (count==1){
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("updateBrand success:{}",pmsBrand);
        }else {
            commonResult =CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}",pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation("删除品牌信息")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id){
        CommonResult commonResult = null;
        int count = service.deleteBrand(id);
        if (count==1){
            commonResult = CommonResult.success(null);
            LOGGER.debug("deleteBrand success:{}",id);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("deleteBrand failed:{}",id);
        }
        return  commonResult;
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum",defaultValue = "1")
                                                        @ApiParam("页码") Integer pageNum,
                                                        @RequestParam(value = "pageSize",defaultValue = "3")
                                                        @ApiParam("每页数量") Integer pageSize){
        List<PmsBrand> list = service.listBrand(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("获取指定商品详情信息")
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id){
        return CommonResult.success(service.getBrand(id));
    }
}
