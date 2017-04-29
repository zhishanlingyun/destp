package com.destp.cla;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
public class ClassLoaderTest {

    public void demo1(){
        ClassLoader cl = ClassLoaderTest.class.getClassLoader();
        for(;null!=cl;cl = cl.getParent()){
            System.out.println(cl);
        }

    }

    public void demo2(){
        SimpleClassLoader cl  = new SimpleClassLoader();
        try {
            Class clazz = cl.loadClass("OutTest");
            Object obj = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("foo",String.class);
            Method set = clazz.getDeclaredMethod("setName",String.class);
            set.invoke(obj,"zhangsan");
            System.out.println(method.invoke(obj,"kkk"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClassLoaderTest().demo2();
    }

}
