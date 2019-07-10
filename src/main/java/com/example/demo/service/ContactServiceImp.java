package com.example.demo.service;

import com.example.demo.dao.contactMapper;
import com.example.demo.entity.*;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContactServiceImp  {
    @Resource
    private contactMapper contactDao;

    public int insert(contact c){
        return contactDao.insert(c);
    }

    public List<contact> selectByExample(contactExample example){
        return contactDao.selectByExample(example);
    }

    public contact selectByPrimaryKey(Integer id) {
        return contactDao.selectByPrimaryKey(id);
    }

    public  int deleteByPrimaryKey(Integer conId){
        return contactDao.deleteByPrimaryKey(conId);
    }

    /**
     * 仪表盘信息
     * @param limit
     * @return
     */
    public List<contact> recentContact(int limit){
        if(limit<0||limit>10){
            limit=10;
        }
        contactExample example=new contactExample();
        example.setOrderByClause("con_id desc");
        PageHelper.startPage(1,limit);
        List<contact> contactList=contactDao.selectByExample(example);
        return  contactList;
    }
}
