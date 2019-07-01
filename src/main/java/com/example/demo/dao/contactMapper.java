package com.example.demo.dao;

import com.example.demo.entity.contact;
import com.example.demo.entity.contactExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface contactMapper {
    int countByExample(contactExample example);

    int deleteByExample(contactExample example);

    int deleteByPrimaryKey(Integer conId);

    int insert(contact record);

    int insertSelective(contact record);

    List<contact> selectByExample(contactExample example);

    contact selectByPrimaryKey(Integer conId);

    int updateByExampleSelective(@Param("record") contact record, @Param("example") contactExample example);

    int updateByExample(@Param("record") contact record, @Param("example") contactExample example);

    int updateByPrimaryKeySelective(contact record);

    int updateByPrimaryKey(contact record);
}