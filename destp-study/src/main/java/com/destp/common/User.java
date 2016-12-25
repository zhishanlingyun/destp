package com.destp.common;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class User {

    private long id;
    private String name;
    private Group group;
    private List<String> friends;
    private List xq;
    private List<Point> points;

    public User() {
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List getXq() {
        return xq;
    }

    public void setXq(List xq) {
        this.xq = xq;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
