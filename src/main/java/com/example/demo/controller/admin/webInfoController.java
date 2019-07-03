package com.example.demo.controller.admin;

import com.example.demo.Ao.RestResponseBo;
import com.example.demo.entity.webInfo;
import com.example.demo.service.WebServiceImp;
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

    @RequestMapping("")
    public String wenInfo(HttpServletRequest request){
         webInfo web=webServiceImp.selectByPrimaryKey(1);
         request.setAttribute("webInfo",web);
        return "admin/webInfo";
    }

    @RequestMapping("/update")
    @ResponseBody
    public RestResponseBo WebUpdate(webInfo web){
        System.out.println(web.getWebname()+"进来了");
        try{
            webServiceImp.updateByPrimaryKey(web);
        }catch (Exception e){
            String msg = "发布失败";
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
