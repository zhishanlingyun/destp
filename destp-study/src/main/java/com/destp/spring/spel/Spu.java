package com.destp.spring.spel;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class Spu {

    private long spuid;

    private String name;

    private Sku sku;

    public long getSpuid() {
        return spuid;
    }

    public void setSpuid(long spuid) {
        this.spuid = spuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }
}
