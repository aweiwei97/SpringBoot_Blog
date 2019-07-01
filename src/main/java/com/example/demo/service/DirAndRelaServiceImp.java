package com.example.demo.service;

import com.example.demo.dao.dirMapper;
import com.example.demo.dao.relationshipMapper;
import com.example.demo.entity.dir;
import com.example.demo.entity.dirExample;
import com.example.demo.entity.relationshipExample;
import com.example.demo.entity.relationshipKey;
import com.example.demo.exception.TipException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DirAndRelaServiceImp {
    @Resource
    private dirMapper dirMapperDao;
    @Resource
    private relationshipMapper relationMapperDao;

public void saveCategories(Integer cid,String categorie){
    if (null == cid) {
        throw new TipException("项目关联id不能为空");
    }
    if (StringUtils.isNotBlank(categorie)) {
        String[] nameArr = StringUtils.split(categorie, ",");
        for (String name : nameArr) {
            this.saveOrUpdate(cid, name);
        }
    }
}

    private void saveOrUpdate(Integer cid, String name) {
        /**
         * 1、确认分类表有此分类
         * 2、取出此分类的id
         * 3、确认分类表是否有此关系
         * 4、无--->添加
         */
        dirExample example=new dirExample();
        example.createCriteria().andNameEqualTo(name);
       List<dir> list= dirMapperDao.selectByExample(example);
       int mid;
       if(list.size()==1){
           mid=list.get(0).getMid();
       }else{
           throw new TipException("查询到多条数据");
       }
       if (mid!=0){
           relationshipExample example1=new relationshipExample();
           example1.createCriteria().andCidEqualTo(cid).andMidEqualTo(mid);
           int count=relationMapperDao.countByExample(example1);
           if(count==0){
               relationshipKey relationship=new relationshipKey();
               relationship.setCid(cid);
               relationship.setMid(mid);
               relationMapperDao.insert(relationship);
           }
       }


    }
    public void deleteById(Integer cid,Integer mid){
        relationshipExample example = new relationshipExample();
        relationshipExample.Criteria criteria=example.createCriteria();
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        relationMapperDao.deleteByExample(example);
    }


}
