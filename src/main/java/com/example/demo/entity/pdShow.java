package com.example.demo.entity;

public class pdShow {
    private Integer imgId;

    private String imgUrl;

    private String imgTitle;

    private String url;

    private Integer authorId;

    private Integer sort;

    public pdShow(Integer imgId, String imgUrl, String imgTitle, String url, Integer authorId, Integer sort) {
        this.imgId = imgId;
        this.imgUrl = imgUrl;
        this.imgTitle = imgTitle;
        this.url = url;
        this.authorId = authorId;
        this.sort = sort;
    }

    public pdShow(String imgUrl, String imgTitle, String url, Integer authorId, Integer sort) {
        this.imgUrl = imgUrl;
        this.imgTitle = imgTitle;
        this.url = url;
        this.authorId = authorId;
        this.sort = sort;
    }

    public pdShow() {
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle == null ? null : imgTitle.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}