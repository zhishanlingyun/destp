package com.destp.acm.stack;

/**
 * Created by zsly on 17-10-30.
 * 逆序栈
 */
public class ReverseStack {

    public static <T> T getBottom(Stack<T> stack){
        T result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            T last = getBottom(stack);
            stack.push(result);
            return last;
        }
    }

    public static <T> void reverse(Stack<T> stack){
        if(stack.isEmpty()){
            return;
        }
        T i = ReverseStack.getBottom(stack);
        reverse(stack);
        stack.push(i);
    }


}
