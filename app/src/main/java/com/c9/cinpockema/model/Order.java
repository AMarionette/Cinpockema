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
    private Date creatTime;
    private Movie movie;
    private double totalPrice;



    public int getId() {
        return id;
    }
    public void setId(int id_) {
        id = id_;
    }

    public Date getCreatTime() {
        return creatTime;
    }
    public void setCreatTime(Date creatTime_) {
        creatTime = creatTime_;
    }

    public Movie getMovie(){
        return movie;
    }
    public void setMovie(Movie movie_ ) {
        movie = movie_;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice_) {
        totalPrice = totalPrice_;
    }



}
