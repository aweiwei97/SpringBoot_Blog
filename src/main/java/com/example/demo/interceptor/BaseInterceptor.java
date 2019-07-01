package com.example.demo.interceptor;

import com.example.demo.entity.admin;
import com.example.demo.utils.AdminCommons;
import com.example.demo.utils.Commoms;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BaseInterceptor implements HandlerInterceptor {
    @Resource
    private Commoms commons;

    @Resource
    private AdminCommons adminCommons;
    //在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        /**
         * request.getRequestURL() 返回全路径
         *
         * request.getRequestURI() 返回除去host（域名或者ip）部分的路径
         *
         * request.getContextPath() 返回工程名部分，如果工程映射为/，此处返回则为空
         *
         * request.getServletPath() 返回除去host和工程名部分的路径
         */

       if(request.getSession().getAttribute("admin")==null){
           response.sendRedirect(request.getContextPath() + "/admin/login");
           return false;
       }
        return true;
    }



    //在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        httpServletRequest.setAttribute("commons", commons);//一些工具类和公共方法
        httpServletRequest.setAttribute("adminCommons", adminCommons);//一些工具类和公共方法

    }

    //在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
