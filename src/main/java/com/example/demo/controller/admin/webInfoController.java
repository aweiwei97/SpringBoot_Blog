package com.example.demo.controller.admin;

import com.example.demo.Ao.RestResponseBo;
import com.example.demo.entity.webInfo;
import com.example.demo.service.LogServiceImp;
import com.example.demo.service.WebServiceImp;
import com.example.demo.utils.Types;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/webInfo")
public class webInfoController {

    @Resource
    private WebServiceImp webServiceImp;
    @Resource
    private LogServiceImp logServiceImp;
    @RequestMapping("")
    public String wenInfo(HttpServletRequest request){
         webInfo web=webServiceImp.selectByPrimaryKey(1);
         request.setAttribute("webInfo",web);
        return "admin/webInfo";
    }

    @RequestMapping("/update")
    @ResponseBody
    public RestResponseBo WebUpdate(webInfo web,HttpServletRequest request){
        System.out.println(web.getWebname()+"进来了");
        try{
            webServiceImp.updateByPrimaryKey(web);
        }catch (Exception e){
            String msg = "发布失败";
            return RestResponseBo.fail(msg);
        }
        logServiceImp.insertLog(Types.UP_WEBINFO.getType(),null,request.getRemoteAddr(),1);

        return RestResponseBo.ok();
    }
}
