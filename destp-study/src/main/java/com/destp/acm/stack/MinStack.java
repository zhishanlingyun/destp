package com.destp.acm.stack;

/**
 * Created by zsly on 17-10-30.
 */
public class MinStack<T> implements EspecialStack<T> {

    private Stack<T> dataStack = new ArrayStack();
    private Stack<T> minStack = new ArrayStack();

    @Override
    public void push(T t) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T getMin() {
        return null;
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
