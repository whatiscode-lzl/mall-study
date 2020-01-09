package com.example.learnmall.dao;

import com.example.learnmall.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/21 16:11
 * 搜索系统中的商品
 **/
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
