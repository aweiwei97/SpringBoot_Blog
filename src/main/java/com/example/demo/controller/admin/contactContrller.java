package com.example.demo.controller.admin;
import com.example.demo.Ao.RestResponseBo;
import com.example.demo.entity.contact;
import com.example.demo.entity.contactExample;
import com.example.demo.service.ContactServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/admin/contact")
public class contactContrller {

    @Resource
    private ContactServiceImp contactService;

    @RequestMapping("")
    public String contact(HttpServletRequest request){
        contactExample example=new contactExample();
        example.setOrderByClause("con_id asc");
        List<contact> contactList=contactService.selectByExample(example);
        request.setAttribute("list",contactList);
        return "admin/contact";


    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    @Transactional
    public RestResponseBo del(@RequestParam Integer id){
       contact c=contactService.selectByPrimaryKey(id);
        if(null==c){
            return RestResponseBo.fail("不存在留言");
        }
        contactService.deleteByPrimaryKey(id);
        return RestResponseBo.ok();
    }
}
