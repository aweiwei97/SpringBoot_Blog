package com.example.demo.utils;

import com.example.demo.constant.WebConst;
import com.example.demo.entity.article;
import com.example.demo.service.ArticleServiceImp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@Component
public class Commoms {
    @Resource
    private ArticleServiceImp articleServiceImp;
    static String pre="user/";



    /**
     * 返回文章链接地址
     *
     * @param
     * @return
     */
    public static String permalink(article a) {
        System.out.println("进来permalink");
        return permalink(a.getCid());
    }

    private static String permalink(Integer cid) {
        return site_url("/article/" +  cid.toString());
    }

    private static String site_url(String s) {
        return site_option("site_url") + s;
    }

    private static String site_option(String key) {
        return site_option(key, "");
    }

    /**
     * 网站配置项
     *
     * @param key
     * @param defalutValue 默认值
     * @return
     */
    public static String site_option(String key, String defalutValue) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        String str = WebConst.initConfig.get(key);
        if (StringUtils.isNotBlank(str)) {
            return str;
        } else {
            return defalutValue;
        }
    }

    /**
     * 格式化unix时间戳为日期
     *
     * @param unixTime
     * @return
     */
    public static String fmtdate(Integer unixTime) {
        return fmtdate(unixTime, "yyyy-MM-dd");
    }

    /**
     * 格式化unix时间戳为日期
     *
     * @param unixTime
     * @param patten
     * @return
     */
    public static String fmtdate(Integer unixTime, String patten) {
        if (null != unixTime && StringUtils.isNotBlank(patten)) {
            return DateKit.formatDateByUnixTime(unixTime, patten);
        }
        return "";
    }

    /**
     * 显示分类
     *
     * @param categories
     * @return
     */
    public static String show_categories(String categories) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(categories)) {
            String[] arr = categories.split(",");
            StringBuffer sbuf = new StringBuffer();
            for (String c : arr) {
                sbuf.append("<a href=\"/category/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
            }
            return sbuf.toString();
        }
        return show_categories("默认分类");
    }

    /**
     * 前缀
     */
    public  String pre(){
        return pre;
    }

}
