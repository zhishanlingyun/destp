package com.destp.acm.stack;

import org.springframework.util.Assert;

/**
 * Created by zsly on 17-10-29.
 */
public class ArrayStack implements Stack {

    private Object[] array;
    private int capacity = 20;
    private int size = 0;
    private int top = 0;

    public ArrayStack() {
        array = new Object[capacity];
    }

    @Override
    public void push(Object o) {
        expansion();
        array[top++] = o;
        size++;
    }

    @Override
    public Object pop() {
        Assert.isTrue(!isEmpty(),"stack is empty!");
        Object o = array[--top];
        size--;
        return o;
    }

    @Override
    public Object peek() {
        if(!isEmpty()){
            int p = top-1;
            return array[p];
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    //扩容
    private void expansion(){
        if(size>=(capacity*0.8)){
            capacity = capacity*2 + size;
            Object[] newArray = new Object[capacity];
            System.arraycopy(array,0,newArray,0,size);
            array = newArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("stack is [bottom]");
        int i = 0;
        while (i<size){
            sb.append(array[i++]).append(",");
        }
        sb.deleteCharAt(sb.length()-1).append(" [top]");
        return sb.toString();
    }
}
