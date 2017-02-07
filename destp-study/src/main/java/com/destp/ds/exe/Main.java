package com.destp.ds.exe;

/**
 * Created with IntelliJ IDEA.
 * User: zsly
 * Date: 17-2-7
 * Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void demo1(){
        MyArrayList list = new MyArrayList(20);
        list.addBefore(1,0);
        list.addBefore(2,0);
        list.addBefore(3,0);
        System.out.println(list);
        list.addBefore(10,1);
        System.out.println(list);
    }

    public static void demo2(){
        MyArrayList list = new MyArrayList(20);
        list.add(0);
        list.add(1);
        list.add(2);
        System.out.println(list);
        list.add(3);
        list.add(5);
        System.out.println(list);
        list.add(6);
        System.out.println(list);
        System.out.println(list.set(99, 2));
        System.out.println(list);
        list.remove(6);
        System.out.println(list);
    }

    public static void demo3(){
        String a = "abc";
        String b = "abc";

        String c = new String("abc");
        String d = new String("abc");

        String e = "a"+"bc";

        System.out.println(a==b);
        System.out.println(c==d);
        System.out.println(a==e);
        System.out.println(e==c);
    }

    public static void demo5(){
        MyList list = new MyList();
        list.add(0);
        list.add(1);
        list.add(2);
        System.out.println(list);
    }

    public static void main(String[] args){
        demo5();
    }

}
