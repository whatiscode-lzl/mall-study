package com.example.learnmall.service;

import com.example.learnmall.dto.OssCallbackResult;
import com.example.learnmall.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author liaozhenglong
 * @Date 2019/11/22 16:19
 *   oss上传管理Service
 **/
public interface OssService {

    /**
     * oss上传策略生成*/
    OssPolicyResult policy();

    /**
     * oss上传成功回调*/
    OssCallbackResult callback(HttpServletRequest request);
}
