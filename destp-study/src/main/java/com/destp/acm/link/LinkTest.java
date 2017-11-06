package com.destp.acm.link;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by zsly on 17-11-4.
 */
public class LinkTest {

    private Linked<Integer> list = new Linked<Integer>();

    private Linked<Integer> list2 = new Linked<Integer>();

    @Before
    public void init(){
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(6);
        list2.add(2);
        list2.add(3);
        list2.add(6);
        list2.add(7);
        list2.add(10);
    }

    @Test
    public void pt(){
        System.out.println(list);
        System.out.println(list.peekFrist());
        System.out.println(list.peekLast());
        System.out.println(list.removeFrist());
        System.out.println(list.removeFrist());
        System.out.println(list.peekFrist());
        System.out.println(list.peekLast());
        System.out.println(list);
        list.removeFrist();
        System.out.println(list);
    }

    @Test
    public void findq(){
        Linked.Node h1 = list.getHead();
        Linked.Node h2 = list2.getHead();
        if(list.isEmpty()||list2.isEmpty()){
            System.out.println("存在空队列,不存在共同元素");
            return;
        }
        StringBuilder sb = new StringBuilder("result: ");
        Linked.Node p1 = h1.next;
        Linked.Node p2 = h2.next;
        while (p1!=h1&&p2!=h2){
            Integer e1 = (Integer) p1.data;
            Integer e2 = (Integer) p2.data;
            if(e1.equals(e2)){
                sb.append(e1).append(",");
                p1 = p1.next;
                p2 = p2.next;
            }else if(e1.compareTo(e2)<0){
                p1 = p1.next;
            }else {
                p2 = p2.next;
            }
        }
        System.out.println(sb);
    }

    public void getLoopLink(){
        Linked<Integer> list1 = new Linked<Integer>();

    }

    @Test
    public void reverse(){
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }

    @Test
    public void link(){
        Linked<Integer> list = new Linked<Integer>();
        list.add(1);
        list.add(2);
        System.out.println(list);
        list.removeFrist();
        System.out.println(list.removeFrist());
        list.add(3);
        System.out.println(list);
    }





}
