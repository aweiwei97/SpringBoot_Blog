package com.example.demo.controller.admin;

import com.example.demo.Ao.RestResponseBo;
import com.example.demo.constant.WebConst;
import com.example.demo.controller.BaseController;
import com.example.demo.entity.admin;
import com.example.demo.exception.TipException;
import com.example.demo.service.AdminServiceImp;
import com.example.demo.service.LogServiceImp;
import com.example.demo.utils.TopUtils;
import com.example.demo.utils.Types;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
@Transactional(rollbackFor = TipException.class)
public class AuthController extends BaseController {
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
    public RestResponseBo doLogin(@RequestParam String username, @RequestParam String password, HttpServletResponse response,HttpServletRequest request, @RequestParam(required = false) String remeber_me) {

        Integer error_count = cache.get("login_error_count");
        if(error_count>3){
            return RestResponseBo.fail("您输入密码已经错误超过3次，请5分钟后尝试");
        }
        try{
       admin a=adminServiceImp.Login(username,password);
       request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY,a);
       logServiceImp.insertLog(Types.LOGIN.getType(),null,request.getRemoteAddr(),1);
        }catch (Exception e){
            error_count++;
            if(error_count>3){
                return RestResponseBo.fail("您输入密码已经错误超过3次，请5分钟后尝试");
            }
            cache.set("login_error_count",error_count,5*60);
            String msg = "登录失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    }
