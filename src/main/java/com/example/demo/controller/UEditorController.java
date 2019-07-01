package com.example.demo.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Controller
public class UEditorController {
    @RequestMapping(value="/ueditorConfig")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

       // String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/admin/ueditor/jsp";
      String  rootPath="D:/IntelliJ IDEA 2018.3.5/demo2/src/main/resources/static/admin/ueditor/jsp";
        //String rootPath= request.getContextPath();
      //  System.out.println(rootPath);
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            System.out.println(exec);
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
