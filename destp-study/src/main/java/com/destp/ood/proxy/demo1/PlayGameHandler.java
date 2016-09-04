package com.destp.ood.proxy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/9/4 0004.
 */
public class PlayGameHandler implements InvocationHandler {

    private Object sourceObj;

    public PlayGameHandler(Object sourceObj) {
        this.sourceObj = sourceObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------------------------------"+proxy.getClass().getName()+"---"+method.getName());
        return method.invoke(this.sourceObj,args);
    }
}
