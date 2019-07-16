package com.example.demo.service;

import com.example.demo.constant.WebConst;
import com.example.demo.dao.articleMapper;
import com.example.demo.dao.contactMapper;
import com.example.demo.dao.relationshipMapper;
import com.example.demo.dto.StatisticsBo;
import com.example.demo.entity.*;
import com.example.demo.exception.TipException;
import com.example.demo.utils.DateKit;
import com.example.demo.utils.Tools;
import com.example.demo.utils.Types;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImp {
    @Resource
    private articleMapper articleDao;

    @Resource DirAndRelaServiceImp dirAndRelaServiceImp;

    @Resource
    private relationshipMapper relationMapperDao;

    @Resource
    private contactMapper contactDao;

       /**
     * 分页查找
     * @param
     * @return
     */
    public PageInfo<article> selectByExampleWithBLOBs(int p,String type,int limit){
        articleExample example=new articleExample();
        example.setOrderByClause("created desc");
        example.createCriteria().andCategoriesEqualTo(type);
        PageHelper.startPage(p, limit);
        List<article> articleList=articleDao.selectByExampleWithBLOBs(example);
        PageInfo<article> articlePageInfo=new PageInfo<>(articleList);
        return  articlePageInfo;
    }

    /**
     * 查找
     * @param
     * @return
     */
    public   List<article> selectByExampleWithBLOBs(articleExample example){
        example.setOrderByClause("created desc");
        List<article> articleList=articleDao.selectByExampleWithBLOBs(example);
        return  articleList;
    }


    /**
     * 插入
     * @param record
     */
    public  void insert(article record){
       if(null==record){
           throw new TipException("文章对象不能为空");
       }
       int titleLength=record.getTitle().length();
       if(titleLength> WebConst.MAX_TITLE_COUNT){
           throw new TipException("文章标题过长");
       }
       int contentTitle=record.getContent().length();
       if(contentTitle>WebConst.MAX_TEXT_COUNT){
           throw new TipException("文章内容过长");
       }
        record.setContent(EmojiParser.parseToAliases(record.getContent()));
        int time = DateKit.getCurrentUnixTime();
        record.setCreated(time);
        record.setModified(time);
        record.setHits(0);
        articleDao.insert(record);
    //更新目录库
        dirAndRelaServiceImp.saveCategories(record.getCid(),record.getCategories());
    }
    /**
     * 插入
     * @param record
     */
    public void update(article record){
        if(null==record){
            throw new TipException("文章对象不能为空");
        }
        int titleLength=record.getTitle().length();
        if(titleLength> WebConst.MAX_TITLE_COUNT){
            throw new TipException("文章标题过长");
        }
        int contentTitle=record.getContent().length();
        if(contentTitle>WebConst.MAX_TEXT_COUNT){
            throw new TipException("文章内容过长");
        }
        record.setContent(EmojiParser.parseToAliases(record.getContent()));
        int time = DateKit.getCurrentUnixTime();
        record.setModified(time);
        articleDao.updateByPrimaryKeyWithBLOBs(record);
        //更新目录库
        dirAndRelaServiceImp.saveCategories(record.getCid(),record.getCategories());
    }
    /**
     * 获取文章
     * @param cid
     * @return
     */
    public article getContents(String  cid){
       if (StringUtils.isNotBlank(cid)){
        if(Tools.isNumber(cid)){
            article a=articleDao.selectByPrimaryKey(Integer.valueOf(cid));
            if(a!=null){
                a.setHits(a.getHits()+1);
                articleDao.updateByPrimaryKey(a);
            }
            return a;
        }
       }
       return null;
    }

    /**
     * 删除
     * @param cid
     */
    public void deleteByCid(int cid) {
        if(cid==9){
            throw  new TipException("不能删除公司简介页");
        }
    article a=this.getContents(cid+"");
    if(a!=null){
        articleDao.deleteByPrimaryKey(cid);
        dirAndRelaServiceImp.deleteById(cid,null);
    }
    }

    /**
     * 更改目录
     * @param d
     */
    public void changeCategory(dir d){
        relationshipExample example=new relationshipExample();
        example.createCriteria().andMidEqualTo(d.getMid());
        List<relationshipKey> relationshipKeyList=relationMapperDao.selectByExample(example);
        if (relationshipKeyList.size()==1){
            int cid=relationshipKeyList.get(0).getCid();
          article a= articleDao.selectByPrimaryKey(cid);
          if(a!=null){
              a.setCategories(d.getName());
              articleDao.updateByPrimaryKey(a);
          }
        }
    }

    /**
     * 仪表盘信息
     * @param limit
     * @return
     */
    public List<article> recentArticle(int limit){
        if(limit<0||limit>10){
            limit=10;
        }
        articleExample example=new articleExample();
        example.setOrderByClause("created desc");
        PageHelper.startPage(1,limit);
        List<article> articleList=articleDao.selectByExampleWithBLOBs(example);
        return  articleList;
    }

    /**
     * 返回文章留言数目
     * @return
     */
        public StatisticsBo getStatistics(){
        int articleCount,contactCount;
        contactExample contactExample=new contactExample();
        articleExample articleExample=new articleExample();
        contactCount=contactDao.countByExample(contactExample);
        articleCount=articleDao.countByExample(articleExample);
        return new StatisticsBo(articleCount,contactCount);
        }
}
