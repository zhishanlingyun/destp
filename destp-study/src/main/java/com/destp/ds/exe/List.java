package com.destp.ds.exe;

/**
 * Created with IntelliJ IDEA.
 * User: zsly
 * Date: 17-2-7
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public interface List<E> {

    public void addBefore(E e,int index);

    public void add(E e);

    public E set(E e,int index);

    public void remove(E e);

}
