package com.example.demo.controller.admin;

import com.example.demo.constant.WebConst;
import com.example.demo.dto.StatisticsBo;
import com.example.demo.entity.*;
import com.example.demo.service.ArticleServiceImp;
import com.example.demo.service.ContactServiceImp;
import com.example.demo.service.LogServiceImp;
import com.example.demo.service.WebServiceImp;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class indexController {

    @Resource
    private LogServiceImp logServiceImp;
    @Resource
    private ContactServiceImp contactServiceImp;
    @Resource
    private ArticleServiceImp articleServiceImp;
    @Resource
    private WebServiceImp webServiceImp;
    /**
     * 仪表盘
     * @param request
     * @return
     */
    @GetMapping(value = {"","/index"})
    public String index(HttpServletRequest request){
        List<article> articles=articleServiceImp.recentArticle(5);
        List<contact> contacts=contactServiceImp.recentContact(5);
        webInfo web=webServiceImp.selectByPrimaryKey(1);
         List<log> logPageInfo=logServiceImp.getLogs();
        StatisticsBo statistics=articleServiceImp.getStatistics();
        request.setAttribute("articles",articles);
        request.setAttribute("contacts",contacts);
        request.setAttribute("logs",logPageInfo);
        request.setAttribute("statistics",statistics);
        request.getSession().setAttribute("webInfo",web);
        return "admin/index";
    }


}
