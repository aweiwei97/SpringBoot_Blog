package com.example.demo.utils;

/**
 * 向枚举中添加新方法
 *
 * 如果打算自定义自己的方法，那么必须在enum实例序列的最后添加一个分号。而且 Java 要求必须先定义 enum 实例。
 */
public enum Types {
    LOGIN("登录后台"), UP_PWD("修改密码"), UP_INFO("修改个人信息"), ADD_SLIDE("增加轮播图"),UP_SLIDE("修改轮播信息"),DE_SLIDE("删除轮播图"),
    DEL_ARTICLE("删除文章"),ADD_ARTICLE("增加文章"),UP_ARTICLE("修改文章"),UP_WEBINFO("修改网站信息");

    private String type;

    public String getType(){
        return type;
    }
    
    public  void setType(String type){
        this.type=type;
    }

    Types(String type){ this.type=type;}
}
