package com.example.demo.controller;

import com.example.demo.Ao.RestResponseBo;
import com.example.demo.dto.ErrorCode;
import com.example.demo.entity.*;
import com.example.demo.exception.TipException;
import com.example.demo.service.ArticleServiceImp;
import com.example.demo.service.ContactServiceImp;
import com.example.demo.service.PdShowServiceImp;
import com.example.demo.service.SlideServiceImp;
import com.example.demo.utils.DateKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class indexContrller {
    @Resource
    private SlideServiceImp slideServiceDao;

    @Resource
    private PdShowServiceImp pdShowServiceDao;

    @Resource
    private ContactServiceImp contactServiceDao;

    @Resource
    private ArticleServiceImp articleServiceDao;

    @RequestMapping(value = {"/","/home"})
    public String topage(HttpServletRequest request){
        slideExample slideExample=new slideExample();
        List<slide> slides=slideServiceDao.findAll(slideExample);
        pdShowExample pdShowExample=new pdShowExample();
        List<pdShow> pdShowList=pdShowServiceDao.selectByExample(pdShowExample);
        request.setAttribute("slides",slides);
        request.setAttribute("pdShowList",pdShowList);
        return "index";
    }
    @RequestMapping("/topage")
    public String toPage(String url){
        return url;
    }

    /**
     * 上传留言
     * @param request
     * @param response
     * @param name
     * @param num
     * @param content
     * @return
     */
    @PostMapping("/contact")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo contact(HttpServletRequest request, HttpServletResponse response, @RequestParam String name,
                                  @RequestParam String num,@RequestParam String content) {
        if(StringUtils.isBlank(name)){
            return  RestResponseBo.fail("请输入姓名");
        }
        if(StringUtils.isBlank(num)){
            return  RestResponseBo.fail("请输入手机号码");
        }
        if(StringUtils.isBlank(content)){
            return  RestResponseBo.fail("请输入留言信息");
        }
        String date= DateKit.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss");
         contact c=new contact(name,num,content,date);
        contactServiceDao.insert(c);
        return RestResponseBo.ok();
    }
@RequestMapping("/news")
    public String newsList(HttpServletRequest request){
        articleExample example=new articleExample( );
        example.setOrderByClause("created desc");
        List<article> list=articleServiceDao.selectByExampleWithBLOBs(example);
        request.setAttribute("articles",list);
        return "news";
}

    /**
     * 文章页
     *
     * @param request 请求
     * @param cid     文章主键
     * @return
     */
    @GetMapping(value = {"article/{cid}", "article/{cid}.html"})
    public String getArticle(HttpServletRequest request, @PathVariable String cid) {
        article a= articleServiceDao.getContents(cid);       //根据id或slug获取文章
        if (null == a|| "draft".equals(a.getStatus())) {
            return "comm_404";   //正在起草中的
        }
        request.setAttribute("article", a);
        request.setAttribute("is_post", true);
        return "post";
    }
}
