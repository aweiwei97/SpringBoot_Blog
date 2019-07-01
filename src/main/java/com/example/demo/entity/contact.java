package com.example.demo.entity;

public class contact {
    private Integer conId;

    private String name;

    private String num;

    private String content;

    private String date;
    public contact() {
    }

    public contact(String name, String num, String content, String date) {
        this.name = name;
        this.num = num;
        this.content = content;
        this.date = date;
    }

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}