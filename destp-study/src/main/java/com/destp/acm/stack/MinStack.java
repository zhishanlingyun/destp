package com.destp.acm.stack;

/**
 * Created by zsly on 17-10-30.
 * getMin() 时间复杂度O(1)
 */
public class MinStack<T extends Comparable> implements EspecialStack<T> {

    private Stack<T> dataStack;
    private Stack<T> minStack;

    public MinStack() {
        dataStack = new ArrayStack();
        minStack = new ArrayStack();
    }

    @Override
    public void push(T t) {
        dataStack.push(t);
        if(minStack.isEmpty()){
            minStack.push(t);
        }else {
            //t<=ms时,入minstack栈,否则不如栈
            Comparable ms = minStack.peek();
            if(ms.compareTo(t)>0){
                minStack.push(t);
            }
        }
    }

    @Override
    public T pop() {
        T result = dataStack.pop();
        if(!minStack.isEmpty()){
            if(result.compareTo(minStack.peek())<=0){
                minStack.pop();
            }
        }
        return result;
    }

    @Override
    public T getMin() {
        return minStack.peek();
    }


    @Override
    public int size() {
        return dataStack.size();
    }

    @Override
    public boolean isEmpty() {
        return dataStack.isEmpty();
    }

    @Override
    public T peek() {
        return dataStack.peek();
    }
}
