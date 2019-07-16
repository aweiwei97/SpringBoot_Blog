package com.example.demo.controller.admin;

import com.example.demo.Ao.RestResponseBo;
import com.example.demo.entity.advantage;
import com.example.demo.exception.TipException;
import com.example.demo.service.advantageServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/advantage")
public class AdvantageController {
    @Resource
    private advantageServiceImp advantageService;

    @GetMapping("")
    public String advantage(HttpServletRequest request){
        advantage adv=advantageService.getAdvantage();
        request.setAttribute("advantage",adv);
        return "admin/advantage";
    }

@PostMapping("/update")
@ResponseBody
    public RestResponseBo update(advantage adv,HttpServletRequest request){
        try {
            advantageService.update(adv);
        }catch (Exception e){
            String msg="修改失败";
            if(e instanceof TipException){
                msg=e.getMessage();
            }
            return RestResponseBo.fail(msg);
        }
    request.setAttribute("advantage",adv);
        return RestResponseBo.ok();
}
}
