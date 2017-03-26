package com.destp.algor;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
public class Test {

    public static void main(String[] args) {

        List list = new ArrayList();
        list.add(99);
        list.add(5);
        list.add(36);
        list.add(7);
        list.add(22);
        list.add(17);
        list.add(46);
        list.add(12);
        list.add(2);
        list.add(19);
        list.add(25);
        list.add(28);
        list.add(1);
        list.add(92);
        PriorityQueue queue = new PriorityQueue(list);
        while (!queue.isEmpty()){
            System.out.printf(queue.poll()+", ");
        }
        System.out.println();

    }

}
