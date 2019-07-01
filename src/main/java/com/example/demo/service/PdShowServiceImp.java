package com.example.demo.service;

import com.example.demo.dao.pdShowMapper;
import com.example.demo.entity.pdShow;
import com.example.demo.entity.pdShowExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PdShowServiceImp {
    @Resource
    private pdShowMapper showMapper;

    public List<pdShow> selectByExample(pdShowExample example){
        return showMapper.selectByExample(example);
    }
    public  int updateByExample (pdShow record,pdShowExample example){
        return showMapper.updateByExample(record,example);
    }

    public pdShow selectByPrimaryKey(Integer slideId){
        return showMapper.selectByPrimaryKey(slideId);
    }


    public void updateByPrimaryKey(pdShow temp) {
        showMapper.updateByPrimaryKey(temp);
    }
     public   int insert(pdShow record){
        return showMapper.insert(record);
  }

    public void deleteByPrimaryKey(Integer id) {
        showMapper.deleteByPrimaryKey(id);
    }
}
