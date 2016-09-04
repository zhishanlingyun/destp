package com.destp.ood.proxy.demo1;

/**
 * Created by Administrator on 2016/9/4 0004.
 */
public class PlayGamer implements PlayGame {

    private String username;

    public PlayGamer(String username) {
        this.username = username;
    }

    @Override
    public boolean login(String username, String password) {
        this.username = username;
        if("123".equals(password)){
            System.out.println(username +" 登录成功");
            return true;
        }
        return false;
    }

    @Override
    public void killBoss() {
        System.out.println(username+" kill boss ...");
    }

    @Override
    public void updateLevel() {
        System.out.println(username+" updateLevel ...");
    }
}
