package com.dk;

import org.apache.http.client.CookieStore;

import java.util.TimerTask;

/**
 * Created by liuli10 on 2016/7/6.
 */
public class Task extends TimerTask {

    private String taskName;
    private int state;
    private String exeTime;
    private String descrbe;
    private String createTime;
    private CookieStore cookieStore;

    public Task(String taskName, int state, String exeTime, String descrbe, String createTime) {
        this.taskName = taskName;
        this.state = state;
        this.exeTime = exeTime;
        this.descrbe = descrbe;
        this.createTime = createTime;
    }

    public Task() {
    }

    @Override
    public void run() {
        System.out.println("==========="+this.getTaskName()+"执行");
        //Daka.toDK(cookieStore);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getExeTime() {
        return exeTime;
    }

    public void setExeTime(String exeTime) {
        this.exeTime = exeTime;
    }

    public String getDescrbe() {
        return descrbe;
    }

    public void setDescrbe(String descrbe) {
        this.descrbe = descrbe;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }
}
