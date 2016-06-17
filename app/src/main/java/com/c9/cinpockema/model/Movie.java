package com.c9.cinpockema.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by marionette on 2016/5/24.
 */
public class Movie {
    private int id;
    private String title;
    private String originalTitle;
    private String imageUrl;//电影海报
    private double rating;//评分
    private int duration;//时长，单位min
    private String genres;//类型：如动作、爱情
    private boolean onShow;//是否上映
    private Bitmap imageBitmap;
    private int wishCount;
    //以下变量未完成
    private String director;//演员
    private String leadingRole;//主演
    private String area;
    private String summary;
    private List<MovieComment> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public boolean isOnShow() {
        return onShow;
    }

    public void setOnShow(boolean onShow) {
        this.onShow = onShow;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public int getWishCount() {
        return wishCount;
    }

    public void setWishCount(int wishCount) {
        this.wishCount = wishCount;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLeadingRole() {
        return leadingRole;
    }

    public void setLeadingRole(String leadingRole) {
        this.leadingRole = leadingRole;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<MovieComment> getComments() {
        return comments;
    }

    public void setComments(List<MovieComment> comments) {
        this.comments = comments;
    }
}
