package com.example.demo.controller.admin;

import com.example.demo.Ao.RestResponseBo;
import com.example.demo.entity.admin;
import com.example.demo.exception.TipException;
import com.example.demo.service.AdminServiceImp;
import com.example.demo.service.LogServiceImp;
import com.example.demo.utils.Types;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
@Transactional(rollbackFor = TipException.class)
public class AuthController {
@Resource
private AdminServiceImp adminServiceImp;

@Resource
private LogServiceImp logServiceImp;

    @GetMapping(value = "/login")
    public String login() {
        return "admin/login";
    }


    /**
     * 登录
     * @param username
     * @param password
     * @param
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public RestResponseBo doLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {

       admin a=adminServiceImp.Login(username,password);
       if (a==null){
           return RestResponseBo.fail("密码错误");
       }
       logServiceImp.insertLog(Types.LOGIN.getType(),null,request.getRemoteAddr(),1);
       request.getSession().setAttribute("admin",a);
        return RestResponseBo.ok();
    }

    }
