package com.destp.acm.stack;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by zsly on 17-10-29.
 */
public class StackTest {

    private EspecialStack<Integer> estack;

    private StackLink<Integer> stackLink;

    private Stack<Integer> stack;

    @Before
    public void init(){
        estack = new MinStack<Integer>();
        /*for(int i=0;i<=50;i++){
            stack.push(i);
        }*/
        estack.push(5);
        estack.push(2);
        estack.push(7);
        estack.push(9);
        estack.push(10);
        estack.push(7);
        estack.push(1);
        estack.push(6);

        stackLink = new StackLink<Integer>();
        stackLink.add(1);
        stackLink.add(2);
        stackLink.add(3);

        stack = new ArrayStack();
        /*for(int i=0;i<10;i++){
            stack.push(i);
        }*/
        stack.push(5);
        stack.push(2);
        stack.push(7);
        stack.push(9);
        stack.push(10);
        stack.push(7);
        stack.push(1);
        stack.push(6);
    }

    @Test
    public void array(){
        Stack<Integer> stack = new ArrayStack();
        for(int i=1;i<=50;i++){
            stack.push(i);
        }
        System.out.println(stack.peek());
        /*while (!stack.isEmpty()){
            System.out.println("出栈 "+stack.pop());
        }*/
    }

    @Test
    public void min(){
        System.out.println("getMin is "+estack.getMin());
        while (!stack.isEmpty()){
            stack.pop();
            System.out.println("getMin is "+estack.getMin());
        }
    }

    @Test
    public void stacklink(){
        System.out.println(stackLink.peek());
        System.out.println(stackLink.pull());
        System.out.println(stackLink.pull());
        stackLink.add(6);
        stackLink.add(7);
        System.out.println(stackLink.pull());
        System.out.println(stackLink.pull());
        System.out.println(stackLink.pull());
        System.out.println(stackLink.pull());
    }

    @Test
    public void findlast(){
        System.out.println(ReverseStack.getBottom(stack));
        System.out.println(ReverseStack.getBottom(stack));
    }

    @Test
    public void reverse(){
        System.out.println(stack.peek());
        ReverseStack.reverse(stack);
        System.out.println(stack.peek());
    }

    @Test
    public void sort(){
        System.out.println(stack);
        StackSort.sort(stack);
        System.out.println(stack);
    }

    @Test
    public void getMaxWin(){
        Integer[] array = {4,3,5,4,3,3,6,7};
        Comparable[] max = MaxWin.getMaxWindow(array,3);
        System.out.println(Arrays.toString(max));
    }

}
