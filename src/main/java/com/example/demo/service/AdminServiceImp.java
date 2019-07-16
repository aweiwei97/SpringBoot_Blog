package com.example.demo.service;

import com.example.demo.dao.adminMapper;
import com.example.demo.entity.admin;
import com.example.demo.entity.adminExample;
import com.example.demo.exception.TipException;
import com.example.demo.utils.TopUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImp {
    @Resource
    private adminMapper adminMapperDao;

    public admin Login(String name, String pwd) {
        if(StringUtils.isBlank(name)||StringUtils.isBlank(pwd)){
            throw  new TipException("用户名和密码不能为空");
        }
        adminExample example = new adminExample();
        adminExample.Criteria criteria=example.createCriteria();
        criteria.andNameEqualTo(name);
        long count=adminMapperDao.countByExample(example);
        if(count<1){
            throw  new TipException("不存在该用户");
        }
      String password= TopUtils.MD5encode(name+pwd);
        criteria.andPasswordEqualTo(password);
        List<admin> adminList=adminMapperDao.selectByExample(example);
        if(adminList.size()!=1){
            throw new TipException("用户名或密码错误");
        }
        return adminList.get(0);
    }

    public void updateByUid(admin temp) {
        if(null==temp||null==temp.getId()){
            throw new TipException("用户为空");
        }
        int i=adminMapperDao.updateByPrimaryKey(temp);
        if(i!=1){
            throw new TipException("用户不唯一");
        }
    }
}
