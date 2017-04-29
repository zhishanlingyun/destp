package com.destp.google;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 不可变集合学习
 */
public class ImmutableDemo {

    public void demoList(){
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        ImmutableList mList = ImmutableList.copyOf(list);
        System.out.println("list {} " + list);
        System.out.println("mList {} " + mList);
        list.add("kkk");
        //mList.add("kkk");
        System.out.println("list {} " + list);
        System.out.println("mList {} " + mList);

    }

    public void demoMap(){
        Map<String, String> forms = ImmutableMap.of();
        /*forms.put("a","1");
        System.out.println(forms);
        forms.put("b", "2");
        System.out.println(forms);*/

    }

    public static void main(String[] args){
        ImmutableDemo demo = new ImmutableDemo();
        //demo.demoList();
        demo.demoMap();
    }

}
