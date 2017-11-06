package com.destp.acm.stack;

/**
 * Created by zsly on 17-10-29.
 * 可以返回getMin功能的栈 O(1)的复杂度
 */
public interface EspecialStack<T> extends Stack<T>{

    public T getMin();

}
