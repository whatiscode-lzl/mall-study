package com.example.learnmall.nosql.mongodb.repository;

import com.example.learnmall.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/21 17:54
 **/
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {

    /**
     * 根据会员id获取所有的商品浏览记录按时间倒序*/
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);//findByMemberIdOrderByCreateTimeDesc
}
