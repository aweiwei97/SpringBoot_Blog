package com.example.demo.controller.admin;

import com.example.demo.Ao.RestResponseBo;
import com.example.demo.constant.WebConst;
import com.example.demo.dto.StatisticsBo;
import com.example.demo.entity.*;
import com.example.demo.exception.TipException;
import com.example.demo.service.*;
import com.example.demo.utils.TopUtils;
import com.example.demo.utils.Types;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class indexController {

    @Resource
    private AdminServiceImp adminServiceImp;
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
         List<log> logPageInfo=logServiceImp.getLogs(20);
        StatisticsBo statistics=articleServiceImp.getStatistics();
        request.setAttribute("articles",articles);
        request.setAttribute("contacts",contacts);
        request.setAttribute("logs",logPageInfo);
        request.setAttribute("statistics",statistics);
        request.getSession().setAttribute("webInfo",web);
        request.getSession().setMaxInactiveInterval(-1);//设置session永不过期
        return "admin/index";
    }
@GetMapping("/profile")
    public  String profile(HttpServletRequest request){
        return "admin/profile";
}

@PostMapping("/profile/password")
@ResponseBody
@Transactional(rollbackFor = TipException.class)
public RestResponseBo upPwd(@RequestParam String oldPassword, @RequestParam String password, HttpServletRequest request, HttpSession session) {
    admin users = (admin) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
    if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(password)) {
        return RestResponseBo.fail("请确认信息输入完整");
    }

    if (!users.getPassword().equals(TopUtils.MD5encode(users.getName() + oldPassword))) {
        return RestResponseBo.fail("旧密码错误");
    }
    if (password.length() < 6 || password.length() > 14) {
        return RestResponseBo.fail("请输入6-14位密码");
    }
    try {
        String pwd = TopUtils.MD5encode(users.getName() + password);
        users.setPassword(pwd);
        adminServiceImp.updateByUid(users);
        logServiceImp.insertLog(Types.UP_PWD.getType(), null, request.getRemoteAddr(), 1);
        //更新session中的数据
        session.setAttribute(WebConst.LOGIN_SESSION_KEY, users);
        return RestResponseBo.ok();
    } catch (Exception e) {
        String msg = "密码修改失败";
        if (e instanceof TipException) {
            msg = e.getMessage();
        }
        return RestResponseBo.fail(msg);
    }
}
    @GetMapping("/logout")
    public String LogOut(HttpServletRequest request){
        request.getSession().removeAttribute(WebConst.LOGIN_SESSION_KEY);
        logServiceImp.insertLog(Types.LOGOUT.getType(), null, request.getRemoteAddr(), 1);
        return "admin/login";
    }

}
