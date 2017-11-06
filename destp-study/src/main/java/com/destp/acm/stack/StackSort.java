package com.destp.acm.stack;

/**
 * Created by zsly on 17-10-31.
 * 使用一个栈对另一个栈排序
 */
public class StackSort {

    public static <T extends Comparable> void sort(Stack<T> stack){
        if(stack.isEmpty()||stack.size()<2) return;
        Stack<T> help = new ArrayStack();
        while (!stack.isEmpty()){
            T cur = stack.pop();
            if(help.isEmpty()){
                help.push(cur);
            }else {
                T top = help.peek();
                if(cur.compareTo(top)<=0){
                    help.push(cur);
                }else {
                    while (!help.isEmpty()&&cur.compareTo(help.peek())>0){
                        T tmp = help.pop();
                        stack.push(tmp);
                    }
                    help.push(cur);
                }
            }
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
    }

}
