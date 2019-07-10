package com.example.demo.controller.admin;

import com.example.demo.Ao.RestResponseBo;

import com.example.demo.entity.slide;
import com.example.demo.entity.slideExample;
import com.example.demo.service.LogServiceImp;
import com.example.demo.service.SlideServiceImp;
import com.example.demo.utils.Types;
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
@RequestMapping("/admin/slide")
public class sllideController {
    @Resource
    private SlideServiceImp slideServiceImp;
    @Resource
    private LogServiceImp logServiceImp;

    public static final String CLASSPATH = commUtils.getUplodFilePath();

    /**
     * 轮播图
     * @param request
     * @return
     */
    @RequestMapping("")
    public String slide(HttpServletRequest request){
        slideExample example=new slideExample();
    example.setOrderByClause("slide_sort asc"); //升序
    List<slide> slides=slideServiceImp.findAll(example);
    request.setAttribute("slides",slides);
    return "admin/slide_img";
    }

    /**
     * 上传轮播图
     * @param request
     * @param file
     * @param title
     * @param firstP
     * @param secondP
     * @param url
     * @param slide_id
     * @param mark
     */
    @PostMapping("/upload")
    @Transactional
public String uploadImg(HttpServletRequest request, @RequestParam MultipartFile[] file, @RequestParam("title") String title,
                        @RequestParam("firstP")String firstP, @RequestParam("secondP")String secondP, @RequestParam("url")String url, String slide_id, String mark, String slide_sort ) {
        String fname = file[0].getOriginalFilename();
        String fkey=null;
        slide s;
        slideExample example = new slideExample();
        //当修改有上传图片时
        if(fname!=null) {
            fkey= commUtils.getFileKey(fname, "轮播图"); //返回 /upload/YY/MM + "/" + UUID.UU32() + "." + 文件后缀
            File f = new File(CLASSPATH + fkey);
            try {
                FileCopyUtils.copy(file[0].getInputStream(), new FileOutputStream(f));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //修改
        if (mark.equals("false")){
            if(fkey==null){
                slide slide=slideServiceImp.selectByPrimaryKey(Integer.valueOf(slide_id) );
                s = new slide(Integer.valueOf(slide_id),slide.getImgUrl(),Integer.valueOf(slide_sort), title, firstP, secondP, url);
            }else{
                s = new slide(Integer.valueOf(slide_id), fkey, Integer.valueOf(slide_sort), title, firstP, secondP, url);
            }
            example.createCriteria().andSlideIdEqualTo(Integer.valueOf(slide_id));
            slideServiceImp.updateByExample(s, example);
            logServiceImp.insertLog(Types.UP_SLIDE.getType(),null,request.getRemoteAddr(),1);

        }else{
            //插入
            /**
             * 1、根据sort_id的升序导出数组
             * 2、找到要插入在它前面的对象，把其及其后的sort_id加1
             * 3、生成新的slide对象slide_sort为要插入的sort
             */

            example.setOrderByClause("slide_sort asc"); //升序
            List<slide> slideList=slideServiceImp.findAll(example);
            for(int i=0;i<slideList.size();i++){
               if(slideList.get(i).getSlideSort()==Integer.valueOf(slide_sort)){
                for (int j=i;j<slideList.size();j++){
                    slide temp=slideServiceImp.selectByPrimaryKey( slideList.get(j).getSlideId());
                    temp.setSlideSort(slideList.get(j).getSlideSort()+1);
                    slideServiceImp.updateByPrimaryKey(temp);
                }
                break;
               }
            }
            s = new slide(fkey, Integer.valueOf(slide_sort), title, firstP, secondP, url);
            slideServiceImp.insert(s);
            logServiceImp.insertLog(Types.ADD_SLIDE.getType(),null,request.getRemoteAddr(),1);

        }
        return "redirect:";
    }

    @PostMapping("/delete")
    @ResponseBody
    @Transactional
    public RestResponseBo del(@RequestParam Integer id, @RequestParam Integer sort,HttpServletRequest request){
      slide slide=slideServiceImp.selectByPrimaryKey(id);
      if(null==slide||slide.getSlideSort()!=sort){
          return RestResponseBo.fail("不存在该轮播图");
      }
      String imgUrl=null;
    slideExample example=new slideExample();
        example.setOrderByClause("slide_sort asc"); //升序
        List<com.example.demo.entity.slide> slideList=slideServiceImp.findAll(example);
        for(int i=0;i<slideList.size();i++){
            if(slideList.get(i).getSlideSort()==Integer.valueOf(sort)){
                imgUrl=slideList.get(i).getImgUrl();
                for (int j=i;j<slideList.size();j++){
                    com.example.demo.entity.slide temp=slideServiceImp.selectByPrimaryKey( slideList.get(j).getSlideId());
                    temp.setSlideSort(slideList.get(j).getSlideSort()-1);
                    slideServiceImp.updateByPrimaryKey(temp);
                }
                break;
            }
        }
        File file=new File(CLASSPATH +imgUrl);
        slideServiceImp.deleteByPrimaryKey(id);
        logServiceImp.insertLog(Types.DE_SLIDE.getType(),null,request.getRemoteAddr(),1);

        return RestResponseBo.ok();
    }
}
