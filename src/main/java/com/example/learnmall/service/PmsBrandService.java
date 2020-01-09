package com.example.learnmall.service;

import com.example.learnmall.mbg.model.PmsBrand;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/20 9:24
 **/
public interface PmsBrandService {

    List<PmsBrand> listAllBrands();
    int createBrand(PmsBrand pmsBrand);
    int updateBrand(Long id,PmsBrand pmsBrand);
    int deleteBrand(Long id);
    List<PmsBrand> listBrand(int pageNum,int pageSize);
    PmsBrand getBrand(Long id);
}
