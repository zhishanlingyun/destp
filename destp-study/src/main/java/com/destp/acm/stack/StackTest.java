package com.destp.acm.stack;

import org.junit.Test;

/**
 * Created by zsly on 17-10-29.
 */
public class StackTest {

    @Test
    public void array(){
        Stack<Integer> stack = new ArrayStack();
        for(int i=1;i<=50;i++){
            stack.push(i);
        }
        while (!stack.isEmpty()){
            System.out.println("出栈 "+stack.pop());
        }
    }

}
