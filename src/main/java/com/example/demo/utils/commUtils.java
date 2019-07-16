package com.example.demo.utils;

import com.example.demo.controller.admin.sllideController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Date;

public class commUtils {
    /**
     * 判断文件是否是图片类型
     *
     * @param imageFile
     * @return
     */
    public static boolean isImage(InputStream imageFile) {
        try {
            Image img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取在CENTOS上的项目static路径
     * @return
     */
    public static String getCentOSPath(){
        String path=null;
        try {
             path=ClassUtils.getDefaultClassLoader().getResource("").toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path+"static";
    }

    /**
     * 获取保存文件的位置,jar所在目录的路径
     *
     * @return
     */
    public static String getUplodFilePath() {
        String path = commUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(1, path.length());
        try {
            path = java.net.URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int lastIndex = path.lastIndexOf("/") + 1;
        path = path.substring(0, lastIndex);
        File file = new File("");
        return file.getAbsolutePath()+"/src/main/resources/static";
    }

    public static String getFileKey(String name,String dir) {
        String prefix = "/upload/"+dir+"/" + DateKit.dateFormat(new Date(), "yyyy/MM");
        if (!new File(sllideController.CLASSPATH + prefix).exists()) {    //目录不存在
            new File(sllideController.CLASSPATH + prefix).mkdirs();            //创建
        }

        name = StringUtils.trimToNull(name);    //去除name首尾的空格
        if (name == null) {
            return prefix + "/" + UUID.UU32() + "." + null;
        } else {
            name = name.replace('\\', '/');
            name = name.substring(name.lastIndexOf("/") + 1);   //获取子串
            int index = name.lastIndexOf(".");      //获取最后一个.的下标
            String ext = null;
            if (index >= 0) {
                ext = StringUtils.trimToNull(name.substring(index + 1));//获取文件的后缀
            }
            return prefix + "/" + UUID.UU32() + "." + (ext == null ? null : (ext));
        }
    }
}
