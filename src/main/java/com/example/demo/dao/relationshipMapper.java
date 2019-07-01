package com.example.demo.dao;

import com.example.demo.entity.relationshipExample;
import com.example.demo.entity.relationshipKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface relationshipMapper {
    int countByExample(relationshipExample example);

    int deleteByExample(relationshipExample example);

    int deleteByPrimaryKey(relationshipKey key);

    int insert(relationshipKey record);

    int insertSelective(relationshipKey record);

    List<relationshipKey> selectByExample(relationshipExample example);

    int updateByExampleSelective(@Param("record") relationshipKey record, @Param("example") relationshipExample example);

    int updateByExample(@Param("record") relationshipKey record, @Param("example") relationshipExample example);
}