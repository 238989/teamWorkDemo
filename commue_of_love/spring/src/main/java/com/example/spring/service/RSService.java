package com.example.spring.service;

import com.example.spring.pojo.RS;

import java.util.List;

public interface RSService {
    /**
     * 查询所有数据
     */
    List<RS> findAll();

    /**
     * 根据ID查询数据
     */
    RS findByID(int id);

    /**
     * 根据审核状态查询数据
     */
    List<RS> findByAudited(String audited);

    /**
     * 根据发起人账号查询数据
     */
    List<RS> findByUid(String uid);

    /**
     * 根据关键词模糊查询数据
     */
    List<RS> findByKeyword(String keyword);

    /**
     * 修改审核状态
     */
    boolean updateAudited(int id, String audited);

    /**
     * 修改其他信息
     */
    boolean updateInfo(RS rs);
}
