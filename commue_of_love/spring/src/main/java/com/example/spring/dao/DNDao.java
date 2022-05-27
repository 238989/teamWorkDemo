package com.example.spring.dao;

import com.example.spring.pojo.DN;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DNDao {

    /**
     * 查询所有数据
     */
    @Select("SELECT * FROM donation")
    List<DN> findAll();

    /**
     * 根据ID查询数据
     *
     */
    @Select("SELECT * from donation WHERE id=#{id}")
    DN findByID(@Param("id") int id);

    /**
     * 根据审核状态查询数据
     */
    @Select("SELECT * FROM donation WHERE audited=#{audited}")
    List<DN> findByAudited(@Param("audited") String audited);

    /**
     * 根据发起人账号查询数据
     */
    @Select("SELECT * FROM donation WHERE uid=#{uid}")
    List<DN> findByUid(@Param("uid") String uid);

    /**
     * 根据关键词模糊查询数据
     */
    @Select("SELECT * FROM donation WHERE title LIKE '%#{keyword}%'")
    List<DN> findByKeyword(@Param("keyword") String keyword);

    /**
     * 修改审核状态
     *
     */
    @Update("UPDATE donation SET audited=#{audited} WHERE id=#{id}")
    boolean updateAudited(int id, String audited);

    /**
     * 修改其他信息
     *
     */
    @Update("UPDATE donation SET title=#{title},kind=#{kind},detail=#{detail},province=#{province},city=#{city},county=#{county},address=#{address},note=#{note},available=#{available} WHERE id=#{id}")
    boolean updateInfo(DN dn);
}
