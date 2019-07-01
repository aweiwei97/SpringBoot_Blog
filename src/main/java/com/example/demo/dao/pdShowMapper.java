package com.example.demo.dao;

import com.example.demo.entity.pdShow;
import com.example.demo.entity.pdShowExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface pdShowMapper {
    int countByExample(pdShowExample example);

    int deleteByExample(pdShowExample example);

    int deleteByPrimaryKey(Integer imgId);

    int insert(pdShow record);

    int insertSelective(pdShow record);

    List<pdShow> selectByExample(pdShowExample example);

    pdShow selectByPrimaryKey(Integer imgId);

    int updateByExampleSelective(@Param("record") pdShow record, @Param("example") pdShowExample example);

    int updateByExample(@Param("record") pdShow record, @Param("example") pdShowExample example);

    int updateByPrimaryKeySelective(pdShow record);

    int updateByPrimaryKey(pdShow record);
}