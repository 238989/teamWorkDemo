package com.example.spring.dao;

import com.example.spring.pojo.RS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RSDao {

    /**
     * 查询所有数据
     */
    @Select("SELECT * FROM resource_sharing")
    List<RS> findAll();

    /**
     * 根据ID查询数据
     *
     */
    @Select("SELECT * from resource_sharing WHERE id=#{id}")
    RS findByID(@Param("id") int id);

    /**
     * 根据审核状态查询数据
     */
    @Select("SELECT * FROM resource_sharing WHERE audited=#{audited}")
    List<RS> findByAudited(@Param("audited") String audited);

    /**
     * 根据发起人账号查询数据
     */
    @Select("SELECT * FROM resource_sharing WHERE uid=#{uid}")
    List<RS> findByUid(@Param("uid") String uid);

    /**
     * 根据关键词模糊查询数据
     */
    @Select("SELECT * FROM resource_sharing WHERE title LIKE '%#{keyword}%'")
    List<RS> findByKeyword(@Param("keyword") String keyword);

    /**
     * 修改审核状态
     *
     */
    @Update("UPDATE resource_sharing SET audited=#{audited} WHERE id=#{id}")
    boolean updateAudited(int id, String audited);

    /**
     * 修改其他信息
     *
     */
    @Update("UPDATE resource_sharing SET title=#{title},kind=#{kind},detail=#{detail},province=#{province},city=#{city},county=#{county},address=#{address},available_time=#{available_time},note=#{note},available=#{available} WHERE id=#{id}")
    boolean updateInfo(RS rs);
}
