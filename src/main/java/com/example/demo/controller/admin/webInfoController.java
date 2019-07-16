package com.example.demo.controller.admin;

import com.example.demo.Ao.RestResponseBo;
import com.example.demo.entity.webInfo;
import com.example.demo.service.LogServiceImp;
import com.example.demo.service.WebServiceImp;
import com.example.demo.utils.Types;
import com.example.demo.utils.commUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/admin/webInfo")
public class webInfoController {

    @Resource
    private WebServiceImp webServiceImp;

    @Resource
    private LogServiceImp logServiceImp;

    //public static final String CLASSPATH = commUtils.getUplodFilePath();
    //CentOS使用路径
    public static final String CLASSPATH = commUtils.getCentOSPath();
    @RequestMapping("")
    public String wenInfo(HttpServletRequest request){
        return "admin/webInfo";
    }

    @RequestMapping("/update")
    public String WebUpdate(HttpServletRequest request, @RequestParam MultipartFile[] file,
                                    @RequestParam("webname")String webname,@RequestParam("weburl")String weburl,@RequestParam("adress")String adress,
                                    @RequestParam("phone")String phone,@RequestParam("email")String email,@RequestParam("intro")String intro,@RequestParam("fileinfo")String fileinfo,
                                    @RequestParam("copyright")String copyright){
      String LogoName=file[0].getOriginalFilename();
      String WeChatName=file[1].getOriginalFilename();
      webInfo web=webServiceImp.selectByPrimaryKey(1);
      String LogoKey=web.getLogoUrl();
      String WechatKey=web.getWechat();
      if(!"".equals(LogoName)) {
          LogoKey = commUtils.getFileKey(LogoName, "网站");
          File file1 = new File(CLASSPATH + LogoKey);
          try {
              FileCopyUtils.copy(file[0].getInputStream(), new FileOutputStream(file1));//复制新的图片
          } catch (IOException e) {
              e.printStackTrace();
          }
          if (web.getLogoUrl() != null) {
              file1 = new File(CLASSPATH + web.getLogoUrl());
              file1.delete();     //删除原来的图片
          }
      }
      if(!"".equals(WeChatName)){
          WechatKey=commUtils.getFileKey(WeChatName,"Web");
          File file2=new File(CLASSPATH+WechatKey);
          try {
              FileCopyUtils.copy(file[1].getInputStream(), new FileOutputStream(file2));
          } catch (IOException e) {
              e.printStackTrace();
          }
          if(web.getWechat()!=null) {
              file2 = new File(CLASSPATH + web.getWechat());
              file2.delete();
          }
      }
      webInfo w=new webInfo(1,webname,weburl,intro,fileinfo,copyright,email,LogoKey,WechatKey,adress,phone);
        try{
            webServiceImp.updateByPrimaryKey(w);
        }catch (Exception e){
           e.printStackTrace();
        }
        logServiceImp.insertLog(Types.UP_WEBINFO.getType(),null,request.getRemoteAddr(),1);
        request.getSession().setAttribute("webInfo",w);//更新session
        return "redirect:/admin/webInfo";
    }
}
