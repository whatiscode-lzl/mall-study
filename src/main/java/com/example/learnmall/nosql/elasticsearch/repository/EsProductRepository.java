package com.example.learnmall.nosql.elasticsearch.repository;

import com.example.learnmall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author liaozhenglong
 * @Date 2019/11/21 15:40
 *
 * 商品的es操作
 **/
public interface EsProductRepository  extends ElasticsearchRepository<EsProduct,Long>{

    /**
     * @param name 商品名称
     * @param subTitle 商品标题
     * @param keywords 关键字
     * @param pageable 分页
     * @return */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name,String subTitle,String keywords,Pageable pageable);
}
