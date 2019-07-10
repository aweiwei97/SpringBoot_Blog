package com.example.demo.service;

import com.example.demo.constant.WebConst;
import com.example.demo.dao.logMapper;
import com.example.demo.entity.log;
import com.example.demo.entity.logExample;
import com.example.demo.utils.DateKit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImp {
    @Resource
    private logMapper logMapperDao;

    /**
     * 插入
     * @param l
     */
    public void insertLog(log l){
        logMapperDao.insert(l);
    }

    /**
     * 插入
     * @param action
     * @param data
     * @param ip
     * @param authorId
     */
    public void insertLog(String action, String data, String ip, Integer authorId) {
       log logs = new log();
        logs.setAction(action);
        logs.setData(data);
        logs.setIp(ip);
        logs.setAuthorId(authorId);
        logs.setCreated(DateKit.getCurrentUnixTime());
        logMapperDao.insertSelective(logs);
    }


    /**
     * 分页显示日志信息
     *
     */
    public List<log> getLogs(){
    logExample example=new logExample();
        example.setOrderByClause("id desc");
    List<log> logList=logMapperDao.selectByExample(example);
    return logList;
}
}
