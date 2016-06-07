package com.c9.cinpockema.model;

/**
 * Created by marionette on 2016/5/24.
 */
public class Seat {

    private int hallId;//影厅id
    private int coordinateX;//座位坐标x
    private int coordinateY;//座位坐标y
    //行号列号用于展示
    private int col;//座位的行号
    private int row;//座位的行号

    private String name;//座位名字：第x排第y座

    private int status;

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
