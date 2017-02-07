package com.destp.ds.exe;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: zsly
 * Date: 17-2-7
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */
public class MyArrayList<E> extends BaseSet<E> implements List<E>,ISet<E> {

    private Object[] array;

    private int capcount = 10;

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int cap) {
        this.capcount = cap;
        array = new Object[capcount];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e)!=-1;
    }

    @Override
    public void addBefore(E e, int index) {
        checkArgs(index);
        for(int j=size;j>0&&j>=index;j--){
            array[j] = array[j-1];
        }
        array[index] = e;
        size++;
    }

    @Override
    public void add(E e) {
        array[size++]=e;
    }

    @Override
    public E set(E e, int index) {
        checkArgs(e);
        checkArgs(index);
        E d = indexOf(index);
        array[index]=e;
        return d;

    }

    @Override
    public void remove(E e) {
        checkArgs(e);
        int i = indexOf(e);
        if(i!=-1){
            for(int j=i;j<size;j++){
                array[j] = array[j+1];
            }
        }
        size--;
    }



    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("MyArrayList{");
        for(int i=0;i<size;i++){
            sb.append(array[i]+", ");
        }
        sb.append("},size is ").append(size);
        return sb.toString();
    }

    private int indexOf(E e){
        for(int i=0;i<size;i++){
            if(array[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    private E indexOf(int i){
        checkArgs(i);
        return (E)array[i];
    }

}
