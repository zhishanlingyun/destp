package com.destp.acm.tree;

/**
 * Created by zsly on 17-11-6.
 */
public class Tree<T> {

    public T data;

    public Tree<T> lift;

    public Tree<T> right;

    public int childsiez;

    public Tree() {
    }

    public Tree(T data) {
        this.data = data ;
    }

    public Tree(T data, Tree<T> lift, Tree<T> right) {
        this.data = data;
        this.lift = lift;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "data=" + data +
                ", size="+ childsiez+
                ", lift=" + lift +
                ", right=" + right +
                '}';
    }
}
