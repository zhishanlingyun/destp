package com.destp.acm.stack;

import java.util.LinkedList;

/**
 * Created by zsly on 17-11-1.
 * 获取数组的窗口最大值
 * 数组长度为n,窗口大小为w,则会产生n-w+1 个窗口最大值
 * 例如
 * 4 3 5 4 3 3 6 7
 * 0 1 2 3 4 5 6 7
 * [4 3 5] 4 3 3 6 7	5	qmax={0}
 * 4 [3 5 4] 3 3 6 7	5	qmax={0,1}
 * 4 3 [5 4 3] 3 6 7	5	qmax={2}
 * 4 3 5 [4 3 3] 6 7	4
 * 4 3 5 4 [3 3 6] 7	6
 * 4 3 5 4 3 [3 6 7]	7
 *
 */
public class MaxWin {

    public static <T extends Comparable> T[] getMaxWindow(T[] array,int w){
        if(null==array||array.length==0||array.length<w){
            return null;
        }
        Comparable[] res = new Comparable[(array.length - w) + 1];
        LinkedList<Integer> qmax = new LinkedList<Integer>();//双端队列
        int index = 0;
        for(int i=0;i<array.length;i++){
            if(qmax.isEmpty()){
                qmax.addLast(i);
            }
            while (!qmax.isEmpty()&&array[qmax.peekLast()].compareTo(array[i])<=0){
                qmax.pollLast();
            }
            qmax.addLast(i);
            if(qmax.peekFirst()==(i-w)){
                qmax.pollFirst();
            }
            if(i>=w-1){
                res[index++] = array[qmax.peekFirst()];
            }
        }
        return (T[]) res;
    }


}
