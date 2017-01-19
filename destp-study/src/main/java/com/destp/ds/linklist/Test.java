package com.destp.ds.linklist;

/**
 * Created by liuli10 on 2017/1/19.
 */
public class Test {

    public static void demo1(){
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(list.size()-1));
        System.out.println(list.get(6));
        list.remove(0);
        list.remove(9);
        list.remove(6);
        System.out.println(list);
        list.clear();
        System.out.println(list);

    }

    public static void demo2(){
        MyDlinkedList<Integer> list = new MyDlinkedList<Integer>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println(list);
        //System.out.println(list.get(0));
        //System.out.println(list.get(list.size()-1));
        //System.out.println(list.get(6));
        //System.out.println(list.get(3));
        //list.remove(0);
        //list.remove(9);
        //list.remove(7);
        //System.out.println(list);
        list.clear();
        System.out.println(list);
        for(int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println(list);
        System.out.println(list.get(9));

    }

    public static void main(String[] args) {
        demo2();
    }
}
