package com.destp.acm.stack;

/**
 * Created by zsly on 17-10-30.
 * 由两个栈实现的队列,支持 add pull peek 操作
 */
public class StackLink<T> {

    private Stack<T> one;
    private Stack<T> two;

    public StackLink() {
        one = new ArrayStack();
        two = new ArrayStack();
    }

    /**
     * 队尾添加
     * @param t
     */
    public void add(T t){
        one.push(t);
    }

    /**
     * 出队列
     * @return
     */
    public T pull(){
        T result = null;
        if(!one.isEmpty()){
            while (!one.isEmpty()){
                T tmp = one.pop();
                two.push(tmp);
            }
            result = two.pop();
            while (!two.isEmpty()){
                T tmp = two.pop();
                one.push(tmp);
            }
            return result;
        }
        return result;
    }

    /**
     * 查看队头元素
     * @return
     */
    public T peek(){
        T result = null;
        while (!one.isEmpty()){
            T tmp = one.pop();
            two.push(tmp);
        }
        result = two.peek();
        while (!two.isEmpty()){
            T tmp = two.pop();
            one.push(tmp);
        }
        return result;
    }
}
