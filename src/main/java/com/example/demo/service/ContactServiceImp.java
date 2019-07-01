package com.example.demo.service;

import com.example.demo.dao.contactMapper;
import com.example.demo.entity.contact;
import com.example.demo.entity.contactExample;
import com.example.demo.entity.pdShow;
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
}
