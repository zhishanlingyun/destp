package com.destp.ds.arraylist;

import com.destp.ds.AbstractList;
import com.destp.ds.IList;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.sun.xml.internal.fastinfoset.sax.SystemIdResolver;

import java.util.Arrays;

/**
 * Created by liuli10 on 2017/1/18.
 */
public class MyArrayList<E> extends AbstractList<E> {

    private Object[] array;

    private int size;

    private int capacity = DEFAULT_CAPACITY;

    public MyArrayList(){
        this(10);
    }

    public MyArrayList(int initial){
        if(initial<0||initial>Integer.MAX_VALUE){
            throw new IllegalArgumentException("Invalid argument!");
        }
        array = new Object[initial];
        size = 0;
    }

    public void add(E o) {
        changeLength();
        array[size++]=o;
    }

    public E get(int i) {
        rangeCheck(i);
        return (E)array[i];
    }

    public boolean remove(E o) {
        //todo 可以优化，不是每次删除都移动位置
        int i = indexOf(o);
        if(i!=-1){
            int mov = size - i -1;
            System.arraycopy(array,i+1,array,i,mov);
            array[--size] = null;
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public boolean contains(E o) {
        int n = indexOf(o);
        return n!=-1;
    }

    public int indexOf(E o) {
        if(!isEmpty()){
            for(int i=0;i<size;i++){
                if(o.equals(array[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    public void clear() {
        for(int i=0;i<size;i++){
            array[i] = null;
        }
    }

    private void changeLength(){
        if(size>=capacity){
            capacity = DEFAULT_CAPACITY << 1;
        }
        array = Arrays.copyOf(array,capacity);
    }

    private void rangeCheck(int index){
        if(index<0||index>size){
            throw new IndexOutOfBoundsException("index is "+index+" size is "+size);
        }
    }
}
