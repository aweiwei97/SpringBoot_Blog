package com.example.demo.dao;

import com.example.demo.entity.article;
import com.example.demo.entity.articleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface articleMapper {
    int countByExample(articleExample example);

    int deleteByExample(articleExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(article record);

    int insertSelective(article record);

    List<article> selectByExampleWithBLOBs(articleExample example);

    List<article> selectByExample(articleExample example);

    article selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") article record, @Param("example") articleExample example);

    int updateByExampleWithBLOBs(@Param("record") article record, @Param("example") articleExample example);

    int updateByExample(@Param("record") article record, @Param("example") articleExample example);

    int updateByPrimaryKeySelective(article record);

    int updateByPrimaryKeyWithBLOBs(article record);

    int updateByPrimaryKey(article record);
}