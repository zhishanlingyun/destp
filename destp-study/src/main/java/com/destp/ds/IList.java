package com.destp.ds;

/**
 * Created by liuli10 on 2017/1/18.
 */
public interface IList<E> {

    public static final int DEFAULT_CAPACITY = 10;

    public void add(E e);

    public E get(int i);

    public boolean remove(E e);

    public int size();

    public boolean isEmpty();

    public boolean contains(E e);

    public int indexOf(E e);

    public void clear();

    public Iterable<E> iterable();

}
