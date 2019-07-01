package com.example.demo.controller.admin;

import com.example.demo.Ao.RestResponseBo;
import com.example.demo.entity.pdShow;
import com.example.demo.entity.pdShowExample;
import com.example.demo.service.PdShowServiceImp;
import com.example.demo.utils.commUtils;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/pdShow")
public class pdShowController {
@Resource
    private PdShowServiceImp showServiceImp;
    public static final String CLASSPATH = commUtils.getUplodFilePath();

    @RequestMapping("")
        public String show(HttpServletRequest request){
       pdShowExample example= new pdShowExample();
        example.setOrderByClause("sort asc"); //升序
        List<pdShow> list=showServiceImp.selectByExample(example);
        request.setAttribute("pd_showList",list);
        return "admin/pd_show";
    }

    /**
     * 上传作品图
     * @param request
     * @param file
     * @param title
     * @param url
     * @param img_id
     * @param mark
     * @param sort
     * @return
     */
    @PostMapping("/upload")
    @Transactional
    public String uploadImg(HttpServletRequest request, @RequestParam MultipartFile[] file, @RequestParam("title") String title,
                            @RequestParam("url")String url, String img_id, String mark, String sort) {
        String fname = file[0].getOriginalFilename();
        String fkey=null;
        pdShow p;
        pdShowExample example= new pdShowExample();
        if(!fname.equals("")) {
            fkey = commUtils.getFileKey(fname, "作品图"); //返回 /upload/YY/MM + "/" + UUID.UU32() + "." + 文件后缀
            File f = new File(CLASSPATH + fkey);
            try {
                FileCopyUtils.copy(file[0].getInputStream(), new FileOutputStream(f));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //修改
        if (mark.equals("false")) {
            if(fkey!=null) {
                p = new pdShow(Integer.valueOf(img_id), fkey, title, url, 1, Integer.valueOf(sort));
            }else{
                pdShow pd= showServiceImp.selectByPrimaryKey(Integer.valueOf(img_id));
                p = new pdShow(Integer.valueOf(img_id), pd.getImgUrl(),title, url, 1, Integer.valueOf(sort));
            }
            example.createCriteria().andImgIdEqualTo(Integer.valueOf(img_id));
            showServiceImp.updateByExample(p,example);
        }else{
            //插入
            /**
             * 1、根据sort_id的升序导出数组
             * 2、找到要插入在它前面的对象，把其及其后的sort_id加1
             * 3、生成新的slide对象slide_sort为要插入的sort
             */

            example.setOrderByClause("sort asc"); //升序
            List<pdShow> List= showServiceImp.selectByExample(example);
            for(int i=0;i<List.size();i++){
                if(List.get(i).getSort()==Integer.valueOf(sort)){
                    for (int j=i;j<List.size();j++){
                        pdShow temp= showServiceImp.selectByPrimaryKey( List.get(j).getImgId());
                        temp.setSort(List.get(j).getSort()+1);
                        showServiceImp.updateByPrimaryKey(temp);
                    }
                    break;
                }
            }
           p = new pdShow(fkey,title,url,1,Integer.valueOf(sort));
            showServiceImp.insert(p);
        }
        return "redirect:";
    }


    /**
     * 删除
     * @param id
     * @param sort
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    @Transactional
    public RestResponseBo del(@RequestParam Integer id, @RequestParam Integer sort){
       pdShow p=showServiceImp.selectByPrimaryKey(id);
        if(null==p||p.getSort()!=sort){
            return RestResponseBo.fail("不存在该轮播图");
        }
        String imgUrl=null;
    pdShowExample example=new pdShowExample();
        example.setOrderByClause("sort asc"); //升序
        List<pdShow>List=showServiceImp.selectByExample(example);
        for(int i=0;i<List.size();i++){
            if(List.get(i).getSort()==Integer.valueOf(sort)){
                imgUrl=List.get(i).getImgUrl();
                for (int j=i;j<List.size();j++){
                   pdShow temp=showServiceImp.selectByPrimaryKey(List.get(j).getImgId());
                    temp.setSort(List.get(j).getSort()-1);
                    showServiceImp.updateByPrimaryKey(temp);
                }
                break;
            }
        }
        File file=new File(CLASSPATH +imgUrl);
        showServiceImp.deleteByPrimaryKey(id);
        return RestResponseBo.ok();
    }

}
