package com.example.demo.entity;

public class relationshipKey {
    private Integer mid;

    private Integer cid;

    public relationshipKey() {
    }

    public relationshipKey(Integer mid, Integer cid) {
        this.mid = mid;
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}