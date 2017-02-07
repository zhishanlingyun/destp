package com.destp.common;

/**
 * Created by liuli10 on 2017/1/18.
 */
public class Test {


    public static void demo1(){
        int n = 0;
        int i = 0;
        //n = i++;
        n = ++i;
        System.out.println(n);
        System.out.println(i);
    }

    public static void demo2(){
        int[] a = {1,2,3,4,5,6,7,8,9};
        System.arraycopy(a,4,a,3,5);
        for(int i : a){
            System.out.printf(i+", ");
        }
    }

    public static void main(String[] args) {
        //demo2();
        System.out.println(1<<13);
    }
}
