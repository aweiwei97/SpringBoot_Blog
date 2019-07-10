package com.example.demo.dto;

import java.io.Serializable;

public class StatisticsBo implements Serializable {
    private int articleCount;
    private int conactCount;

    public StatisticsBo(int articleCount, int conactCount) {
        this.articleCount = articleCount;
        this.conactCount = conactCount;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public int getConactCount() {
        return conactCount;
    }

    public void setConactCount(int conactCount) {
        this.conactCount = conactCount;
    }
}
