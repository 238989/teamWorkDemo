package com.example.spring.service;

import com.example.spring.bean.DN;

import java.util.List;

public interface DNService {
    /**
     * 查询所有数据
     */
    List<DN> findAll();

    /**
     * 根据ID查询数据
     */
    DN findByID(int id);

    /**
     * 根据审核状态查询数据
     */
    List<DN> findByAudited(String audited);

    /**
     * 根据发起人账号查询数据
     */
    List<DN> findByUid(String uid);

    /**
     * 根据关键词模糊查询数据
     */
    List<DN> findByKeyword(String keyword);

    /**
     * 修改审核状态
     */
    boolean updateAudited(int id, String audited);

    /**
     * 修改其他信息
     */
    boolean updateInfo(DN dn);
}
