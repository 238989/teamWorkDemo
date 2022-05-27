package com.example.spring.service;

import com.example.spring.pojo.VL;

import java.util.List;

public interface VLService {
    /**
     * 查询所有数据
     */
    List<VL> findAll();

    /**
     * 根据ID查询数据
     */
    VL findByID(int id);

    /**
     * 根据审核状态查询数据
     */
    List<VL> findByAudited(String audited);

    /**
     * 根据发起人账号查询数据
     */
    List<VL> findByUid(String uid);

    /**
     * 根据关键词模糊查询数据
     */
    List<VL> findByKeyword(String keyword);

    /**
     * 修改审核状态
     */
    boolean updateAudited(int id, String audited);

    /**
     * 修改其他信息
     */
    boolean updateInfo(VL vl);
}
