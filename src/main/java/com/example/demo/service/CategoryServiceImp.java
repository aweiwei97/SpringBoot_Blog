package com.example.demo.service;

import com.example.demo.dao.dirMapper;
import com.example.demo.dao.relationshipMapper;
import com.example.demo.entity.dir;
import com.example.demo.entity.dirExample;
import com.example.demo.entity.relationshipExample;
import com.example.demo.exception.TipException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImp {
    @Resource
    private dirMapper dirMapperDao;


    @Resource
    private ArticleServiceImp articleServiceDao;
    @Resource
    private relationshipMapper relationMapperDao;

    public  void insert(String name,Integer id){
        dir d=new dir();
        if(StringUtils.isNotBlank(name)){
            dirExample example=new dirExample();
            example.createCriteria().andNameEqualTo(name);
            List<dir> list= dirMapperDao.selectByExample(example);
            if(list.size()!=0){
                throw  new TipException("已经存在该目录");
            } else if(id!=null){
                d.setName(name);
                d.setMid(id);
                dirMapperDao.updateByPrimaryKey(d);
                //更新关联文章目录
                articleServiceDao.changeCategory(d);
            }else{
                d.setName(name);
                dirMapperDao.insert(d);
            }
        }

    }

    public void delete(int mid) {

        dirMapperDao.deleteByPrimaryKey(mid);
        relationshipExample example=new relationshipExample();
        example.createCriteria().andMidEqualTo(mid);
        relationMapperDao.deleteByExample(example);
    }

    public  List<dir> getMetaList(dirExample example){
        return dirMapperDao.selectByExample(example);
    }

}
