package com.destp.spring.spel;

import jodd.util.StringUtil;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class Sku {

    public static final String MAX = "100000000";

    private long skuid;

    private String name;

    private float price;

    private String catalg;

    public Sku() {
    }

    public Sku(long skuid, String name, float price) {
        this.skuid = skuid;
        this.name = name;
        this.price = price;
    }

    public long getSkuid() {
        return skuid;
    }

    public void setSkuid(long skuid) {
        this.skuid = skuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCatalg() {
        return catalg;
    }

    public void setCatalg(String catalg) {
        this.catalg = catalg;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "skuid=" + skuid +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
