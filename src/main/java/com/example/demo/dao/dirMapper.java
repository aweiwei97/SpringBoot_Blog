package com.example.demo.dao;

import com.example.demo.entity.dir;
import com.example.demo.entity.dirExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface dirMapper {
    int countByExample(dirExample example);

    int deleteByExample(dirExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(dir record);

    int insertSelective(dir record);

    List<dir> selectByExample(dirExample example);

    dir selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("record") dir record, @Param("example") dirExample example);

    int updateByExample(@Param("record") dir record, @Param("example") dirExample example);

    int updateByPrimaryKeySelective(dir record);

    int updateByPrimaryKey(dir record);
}