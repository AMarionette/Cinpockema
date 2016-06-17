package com.c9.cinpockema.model;

import java.util.Date;

/**
 * Created by marionette on 2016/5/24.
 */
public class MovieComment {
    private int id;//评论id，唯一
    private User user;
    private int movieId;//所评论的电影
    private double score;//评分
    private String content;//内容
    private Long createTime;//评论时间


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
