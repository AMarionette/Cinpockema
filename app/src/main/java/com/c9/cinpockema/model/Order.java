package com.c9.cinpockema.model;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.List;

/**
 * Created by marionette on 2016/5/24.
 */
public class Order {
    private int id;
    //    //订单状态，枚举类型
////    private Enum<> state;
//    private String exchangeTicketCode;
//    private Date creatTime;
//    private Date payTime;
//    private String userName;
//    private List<Ticket> tickets;
//    private double total;
    private String creatTime;
    private Movie movie;
    private String totalPrice;
    private String movieName;



    public int getId() {
        return id;
    }
    public void setId(int id_) {
        id = id_;
    }

    public String getCreatTime() {
        return creatTime;
    }
    public void setCreatTime(String creatTime_) {
        creatTime = creatTime_;
    }

    public Movie getMovie(){
        return movie;
    }
    public void setMovie(Movie movie_ ) {
        movie = movie_;
    }

    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName_) {
        movieName = movieName_;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice_) {
        totalPrice = totalPrice_;
    }



}
