package com.example.demo.service;

import com.example.demo.dao.slideMapper;
import com.example.demo.entity.slide;
import com.example.demo.entity.slideExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SlideServiceImp {
    @Resource
    private slideMapper slideDao;
    public List<slide> findAll(slideExample example) {

        return  slideDao.selectByExample(example);
    }

    public void updateByExample(slide s, slideExample example) {
        slideDao.updateByExample(s,example);
    }

    public slide selectByPrimaryKey(Integer slideId) {
        return slideDao.selectByPrimaryKey(slideId);
    }

    public void updateByPrimaryKey(slide temp) {
    slideDao.updateByPrimaryKey(temp);
    }

    public void insert(slide s) {
        slideDao.insert(s);
    }

    public void deleteByPrimaryKey(Integer id) {
        slideDao.deleteByPrimaryKey(id);
    }
}
