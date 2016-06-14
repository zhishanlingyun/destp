package com.model;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class CommonModel {

    private static CommonModel instance = new CommonModel();

    public static CommonModel getInstance(){
        return instance;
    }

    private CommonModel(){}

    private String name = "CommonModel";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
