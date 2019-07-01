package com.example.demo.entity;

public class slide {
    private Integer slideId;

    private String imgUrl;

    private Integer slideSort;

    private String title;

    private String firstP;

    private String secondP;

    private String url;

    public slide(Integer slideId, String imgUrl, Integer slideSort, String title, String firstP, String secondP, String url) {
        this.slideId = slideId;
        this.imgUrl = imgUrl;
        this.slideSort = slideSort;
        this.title = title;
        this.firstP = firstP;
        this.secondP = secondP;
        this.url = url;
    }

    public slide(String imgUrl, Integer slideSort, String title, String firstP, String secondP, String url) {
        this.imgUrl = imgUrl;
        this.slideSort = slideSort;
        this.title = title;
        this.firstP = firstP;
        this.secondP = secondP;
        this.url = url;
    }

    public slide() {
    }

    public Integer getSlideId() {
        return slideId;
    }

    public void setSlideId(Integer slideId) {
        this.slideId = slideId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getSlideSort() {
        return slideSort;
    }

    public void setSlideSort(Integer slideSort) {
        this.slideSort = slideSort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFirstP() {
        return firstP;
    }

    public void setFirstP(String firstP) {
        this.firstP = firstP == null ? null : firstP.trim();
    }

    public String getSecondP() {
        return secondP;
    }

    public void setSecondP(String secondP) {
        this.secondP = secondP == null ? null : secondP.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}