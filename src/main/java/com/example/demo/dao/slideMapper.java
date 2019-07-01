package com.example.demo.dao;

import com.example.demo.entity.slide;
import com.example.demo.entity.slideExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface slideMapper {
    int countByExample(slideExample example);

    int deleteByExample(slideExample example);

    int deleteByPrimaryKey(Integer slideId);

    int insert(slide record);

    int insertSelective(slide record);

    slide selectByPrimaryKey(Integer slideId);

    int updateByExampleSelective(@Param("record") slide record, @Param("example") slideExample example);

    int updateByExample(@Param("record") slide record, @Param("example") slideExample example);

    int updateByPrimaryKeySelective(slide record);

    int updateByPrimaryKey(slide record);

    List<slide> selectByExample(slideExample example);
}