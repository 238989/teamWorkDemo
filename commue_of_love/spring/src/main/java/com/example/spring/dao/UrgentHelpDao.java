package com.example.spring.dao;

import com.example.spring.bean.UrgentHelp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UrgentHelpDao {

    /**
     * 查询所有数据
     */
    @Select("SELECT * FROM urgent_help")
    List<UrgentHelp> findAll();

    /**
     * 根据ID查询数据
     *
     */
    @Select("SELECT * from urgent_help WHERE id=#{id}")
    UrgentHelp findByID(@Param("id") int id);

    /**
     * 根据审核状态查询数据
     */
    @Select("SELECT * FROM urgent_help WHERE audited=#{audited}")
    List<UrgentHelp> findByAudited(@Param("audited") String audited);

    /**
     * 根据发起人账号查询数据
     */
    @Select("SELECT * FROM urgent_help WHERE uid=#{uid}")
    List<UrgentHelp> findByUid(@Param("uid") String uid);

    /**
     * 根据关键词模糊查询数据
     */
    @Select("SELECT * FROM urgent_help WHERE title LIKE '%#{keyword}%'")
    List<UrgentHelp> findByKeyword(@Param("keyword") String keyword);

    /**
     * 修改审核状态
     *
     */
    @Update("UPDATE urgent_help SET audited=#{audited} WHERE id=#{id}")
    boolean updateAudited(int id, String audited);

    /**
     * 修改进度
     *
     */
    @Update("UPDATE urgent_help SET completed=#{completed} WHERE id=#{id}")
    boolean updateCompleted(int id, String completed);

    /**
     * 修改其他信息
     *
     */
    @Update("UPDATE urgent_help SET title=#{title},kind=#{kind},detail=#{detail},province=#{province},city=#{city},county=#{county},address=#{address},level=#{level},note=#{note} WHERE id=#{id}")
    boolean updateInfo(UrgentHelp urgentHelp);
}
