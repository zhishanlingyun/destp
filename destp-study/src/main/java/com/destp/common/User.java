package com.destp.common;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class User {

    private long id;
    private String name;
    //private Group group;
    //private List<String> friends;
    //private List xq;
    //private List<Point> points;

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
