package com.example.demo.controller.admin;

import com.example.demo.Ao.RestResponseBo;
import com.example.demo.constant.WebConst;
import com.example.demo.entity.dir;
import com.example.demo.entity.dirExample;
import com.example.demo.exception.TipException;
import com.example.demo.service.CategoryServiceImp;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class categoryController {
    @Resource
    private CategoryServiceImp categoryServiceImp;


    /**
     * 分类页
     * @param request
     * @return
     */
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        dirExample example=new dirExample();
        List<dir> categories = categoryServiceImp.getMetaList(example);
        request.setAttribute("categories", categories);
        return "admin/category";
    }

    /**
     * 保存分类
     * @param cname
     * @param mid
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveCategory(@RequestParam String cname, @RequestParam Integer mid) {
        try{
            categoryServiceImp.insert(cname,mid);
        }catch (Exception e){
            String msg = "分类保存失败";
            return RestResponseBo.fail(msg);
        }

        return RestResponseBo.ok();
    }
    /**
     * 删除分类
     * @param mid
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam int mid) {
        try {
            categoryServiceImp.delete(mid);
        } catch (Exception e) {
            String msg = "删除失败";
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
