package com.destp.common;

import java.util.Arrays;

/**
 * Created by zsly on 17-2-4.
 */
public class Obj {

    public void foo(User user){
        System.out.println("=====foo "+user);
        user.setName("foo-name");
        System.out.println("=====foo change attr "+user);
        user = new User(000,"000");
        System.out.println("=====foo change addr "+user);
    }

    public User fooUser(User user){
        user.setName("foo-name");
        System.out.println("=====foo change attr "+user);
        user = new User(000,"000");
        return user;
    }

    public void demo3(){
        User user = new User(999,"zhangsan");
        System.out.println("main before "+user);
        User tmp = user;
        user = fooUser(user);
        System.out.println("main after "+user);
        System.out.println(tmp);
    }

    public void demo1(){
        User user = new User(999,"zhangsan");
        System.out.println("main before "+user);
        foo(user);
        System.out.println("main after "+user);
    }

    public void fooArray(int[] array){
        array = null;
        System.out.println(Arrays.toString(array));
        array = new int[]{0, 0, 0};
        System.out.println(Arrays.toString(array));
    }

    public void demo2(){
        int[] array = new int[]{9,9,9};
        System.out.println(Arrays.toString(array));
        fooArray(array);
        Arrays.toString(array);
    }

    public static void main(String[] args) {
        Obj obj = new Obj();
        //obj.demo1();
        //obj.demo2();
        obj.demo3();

    }

}
