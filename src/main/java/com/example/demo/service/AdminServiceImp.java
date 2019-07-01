package com.example.demo.service;

import com.example.demo.dao.adminMapper;
import com.example.demo.entity.admin;
import com.example.demo.entity.adminExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImp {
    @Resource
    private adminMapper adminMapperDao;
    public admin Login(String name,String paw) {
        adminExample example = new adminExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(paw);
        List<admin> adminList = adminMapperDao.selectByExample(example);
      if(adminList.size()==1){
         return adminList.get(0);
        }else {
          return  null;
      }
    }
}
