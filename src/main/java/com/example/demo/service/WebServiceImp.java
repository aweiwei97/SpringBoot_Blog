package com.example.demo.service;

import com.example.demo.dao.webInfoMapper;
import com.example.demo.entity.webInfo;
import com.example.demo.exception.TipException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WebServiceImp {

    @Resource
    private webInfoMapper webInfoMapperDao;

    /**
     * 查找
     * @param id
     * @return
     */
   public webInfo selectByPrimaryKey(Integer id){
        return webInfoMapperDao.selectByPrimaryKey(id);
    }

    public  void updateByPrimaryKey(webInfo record){
       if(record.getWebname()==null){
           throw new TipException("网站名不能为空");
       }
       if(record.getId()==null){
           throw  new TipException("id不能为空");
       }
       if(record.getCopyright()==null){
           throw new TipException("版权不能为空");
       }
       if(record.getFileinfo()==null){
           throw new TipException("备案信息不能为空");
       }
       webInfoMapperDao.updateByPrimaryKey(record);
    }



}
