package com.example.spring.service;

import com.example.spring.bean.UrgentHelp;

import java.util.List;

public interface UrgentHelpService {
    /**
     * 查询所有数据
     */
    List<UrgentHelp> findAll();

    /**
     * 根据ID查询数据
     */
    UrgentHelp findByID(int id);

    /**
     * 根据审核状态查询数据
     */
    List<UrgentHelp> findByAudited(String audited);

    /**
     * 根据发起人账号查询数据
     */
    List<UrgentHelp> findByUid(String uid);

    /**
     * 根据关键词模糊查询数据
     */
    List<UrgentHelp> findByKeyword(String keyword);

    /**
     * 修改审核状态
     */
    boolean updateAudited(int id, String audited);

    /**
     * 修改进度
     */
    boolean updateCompleted(int id, String completed);

    /**
     * 修改其他信息
     */
    boolean updateInfo(UrgentHelp urgentHelp);
}
