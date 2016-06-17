package com.c9.cinpockema.model;

import java.util.List;

/**
 * Created by marionette on 2016/5/25.
 */
public class Cinema {
    private int id;
    private int cityId;//所属城市ID
    private String name;//电影院名字
    private String introduction;//影院介绍
    private String address;//地址
    private String phone;//影院电话
    private double score;//评分
    //影院位置信息
    private double latitude;//纬度
    private double longitude;//经度
    //以下未定
    private List<CinemaComment> comments;//影院评论
    private List<Hall> halls;//影厅
    private List<Movie> movies;//播放的电影列表
    private List<Screening> screenings;//场次




    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<CinemaComment> getComments() {
        return comments;
    }

    public void setComments(List<CinemaComment> comments) {
        this.comments = comments;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }
}
