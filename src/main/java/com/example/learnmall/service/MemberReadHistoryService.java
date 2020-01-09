package com.example.learnmall.service;

import com.example.learnmall.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/21 17:59
 **/
public interface MemberReadHistoryService {

    /**
     * 生成浏览记录*/
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     * */
    int delete(List<String> ids);

    /**
     * 获取用户浏览记录*/
    List<MemberReadHistory> list(Long memberId);
}
