package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.ActionEnter;
import com.example.demo.controller.admin.AuthController;
import com.example.demo.service.LogServiceImp;
import com.example.demo.utils.Types;
import com.example.demo.utils.commUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;

@Controller
public class UEditorController {
    @Resource
    private LogServiceImp logServiceImp;
    private static final Logger LOGGER = LoggerFactory.getLogger(UEditorController.class);
    @RequestMapping(value="/ueditorConfig")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");



        try {
            //CENTOS 上

            String rootPath=commUtils.getCentOSPath()+"/ueditor/jsp";
            //本地,去掉前面的“/"
            // rootPath=rootPath.substring(1,rootPath.length()-1);
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logServiceImp.insertLog(e.getMessage(), null, request.getRemoteAddr(), 1);
      }

    }



}
