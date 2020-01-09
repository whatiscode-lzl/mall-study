package com.example.learnmall.controller;

import com.example.learnmall.common.api.CommonPage;
import com.example.learnmall.common.api.CommonResult;
import com.example.learnmall.nosql.elasticsearch.document.EsProduct;
import com.example.learnmall.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/21 16:30
 **/
@Api(tags = "EsProductController",description = "搜索商品管理")
@Controller
@RequestMapping(value = "/esProduct")
public class EsProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductController.class);
    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库商品到ES中")
    @RequestMapping(value = "/importAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> importAllList(){
        return CommonResult.success(esProductService.importAll());
    }

    @ApiOperation(value = "根据商品id删除商品")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable("id") Long id){
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "批量删除商品")
    @RequestMapping(value = "/delete/batch",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@RequestParam("ids")List<Long> ids){
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<EsProduct> create(@PathVariable Long id){
        EsProduct esProduct =esProductService.create(id);
        if (esProduct == null){
            return CommonResult.failed("添加商品失败");
        }
        return CommonResult.success(esProduct);
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        Page<EsProduct> esProducts= esProductService.search(keyword,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(esProducts));
    }
}
