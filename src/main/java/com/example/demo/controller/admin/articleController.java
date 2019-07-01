package com.example.demo.controller.admin;
import com.example.demo.Ao.RestResponseBo;
import com.example.demo.entity.article;
import com.example.demo.entity.articleExample;
import com.example.demo.entity.dir;
import com.example.demo.entity.dirExample;
import com.example.demo.exception.TipException;
import com.example.demo.service.ArticleServiceImp;
import com.example.demo.service.CategoryServiceImp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/admin/article")
public class articleController {
@Resource
    private ArticleServiceImp  articleServiceDao;
    @Resource
    private CategoryServiceImp categoryServiceImp;

    /**
     * 文章列表
     * @param request
     * @return
     */
    @GetMapping(value = "")
    public String index( HttpServletRequest request) {
        articleExample example = new articleExample();
        example.setOrderByClause("created desc");
        example.createCriteria().andTypeEqualTo("post");
        List<article> articles=articleServiceDao.selectByExampleWithBLOBs(example);
        dirExample example2=new dirExample();
        List<dir> categories = categoryServiceImp.getMetaList(example2);
        request.setAttribute("categories", categories);
        request.setAttribute("articles", articles);
        return "admin/article_list";
    }




    @GetMapping("/publish")
    public String newArticle(){
        return "/admin/article_edit";
    }

    /**
     * 新发布文章
     * @param a
     * @param request
     * @return
     */
    @PostMapping("/publish")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo publishArticle(article a, HttpServletRequest request) {
       a.setType("post");
    if(StringUtils.isBlank(a.getCategories())){
        a.setCategories("默认分类");
    }
    try{
        articleServiceDao.insert(a);
    }catch (Exception e){
        String msg = "文章发布失败";
        return RestResponseBo.fail(msg);
    }
            return RestResponseBo.ok();
        }


    /**
     * 删除文章
     * @param cid
     * @param request
     * @return
     */
    @RequestMapping("/delete")
@ResponseBody
@Transactional(rollbackFor = TipException.class)
public RestResponseBo delete(@RequestParam int cid, HttpServletRequest request) {
    try {
        articleServiceDao.deleteByCid(cid);
        //logService.insertLog(LogActions.DEL_ARTICLE.getAction(), cid+"", request.getRemoteAddr(), this.getUid(request));
    } catch (Exception e) {
        String msg = "文章删除失败";
        return RestResponseBo.fail(msg);
    }
    return RestResponseBo.ok();
}




}
