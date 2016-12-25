package com.destp.sharend.shop.biz.home.dto;

/**
 * Created by Administrator on 2016/12/25 0025.
 */
public class ChartDto {

    private String name;
    private int y;

    public ChartDto() {
    }

    public ChartDto(String name, int y) {
        this.name = name;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
