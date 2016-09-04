package com.destp.ood.proxy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/9/4 0004.
 */
public class Client {

    public static void main(String[] args){
        PlayGame pg = new PlayGamer("张三");
        InvocationHandler handler = new PlayGameHandler(pg);
        PlayGame proxy = (PlayGame)Proxy.newProxyInstance(pg.getClass().getClassLoader(),new Class[]{PlayGame.class},handler);
        proxy.login("张三","123");
        proxy.killBoss();
        proxy.updateLevel();
    }
}
