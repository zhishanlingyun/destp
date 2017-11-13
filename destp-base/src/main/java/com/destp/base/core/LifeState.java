package com.destp.base.core;

/**
 * Created by zsly on 17-11-10.
 */
public enum LifeState {

    Ready(0,"准备"),
    Running(1,"运行中"),
    Pause(2,"暂停"),
    //Recovery(3,"恢复"),
    Stop(4,"停止");


    private int code;
    private String desc;

    LifeState(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
