package com.example.demo.service;

import com.example.demo.dao.advantageMapper;
import com.example.demo.entity.advantage;
import com.example.demo.exception.TipException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class advantageServiceImp {
@Resource
    private advantageMapper advantageMapperDao;

    /**
     * 查找
     * @return
     */
    public advantage getAdvantage(){
    return advantageMapperDao.selectByPrimaryKey(1);
}

    /**
     * 修改
     * @param temp
     */
    public void update(advantage temp){
    if(temp==null||temp.getId()==null){
        throw new TipException("不能为空");
    }
   advantageMapperDao.updateByPrimaryKeySelective(temp);
}
}
