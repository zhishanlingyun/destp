package com.destp.ds.arraylist;

import com.destp.ds.IList;

/**
 * Created by liuli10 on 2017/1/18.
 */
public class Test {

    public static void main(String[] args) {
        IList<Integer> a = new MyArrayList<Integer>();
        for(int i=0;i<20;i++){
            a.add(i);
        }
        for(int i=0;i<a.size();i++){
            System.out.printf(a.get(i)+", ");
        }
        System.out.println(a.contains(10));
        System.out.println(a.contains(5));
        System.out.println(a.get(7));
        a.remove(3);
        //a.remove(8);
        for(int i=0;i<a.size();i++){
            System.out.printf(a.get(i)+", ");
        }
    }

}
