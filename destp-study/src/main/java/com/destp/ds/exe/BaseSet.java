package com.destp.ds.exe;

/**
 * Created with IntelliJ IDEA.
 * User: zsly
 * Date: 17-2-7
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */
public class BaseSet<E> {

    protected int size;

    protected void checkArgs(E e){
        if(null==e){
            throw new IllegalArgumentException("args is null");
        }
    }

    protected void checkArgs(int index){
        if(index==0) return;
        if(index>size){
            throw new IllegalArgumentException("over size, max size is "+size+" cur index is "+index);
        }
    }



}
