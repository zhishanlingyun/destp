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

    /**
     * 查看栈顶元素,不出栈
     * @return
     */
    public T peek();

    public int size();

    public boolean isEmpty();

}
