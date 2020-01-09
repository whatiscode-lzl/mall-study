package com.example.learnmall.service;

import com.example.learnmall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/21 15:47
 *
 * 商品搜索管理service
 **/
public interface EsProductService {

    /**
     * 从数据库中导入所有商品到ES*/
    int importAll();

    /**
     * 根据商品id删除商品*/
    void delete(Long id);

    /**
     * 根据商品id创建商品*/
    EsProduct create(Long id);

    /**
     * 批量删除商品*/
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索商品名称或者副标题*/
    Page<EsProduct> search(String keywords,Integer pageNum,Integer pageSize);

}
