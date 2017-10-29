package com.destp.acm.stack;

/**
 * Created by zsly on 17-10-29.
 */
public interface Stack<T> {

    /**
     * 入栈
     * @param t
     */
    public void push(T t);

    /**
     * 出栈
     * @return
     */
    public T pop();

    public int size();

    public boolean isEmpty();

}
