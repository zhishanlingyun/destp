package com.destp.ds.exe;

/**
 * Created by zsly on 17-2-7.
 */
public class MyDoubleLinkList<E> extends BaseSet<E> implements List<E>,ISet<E> {

    //双向循环环链表试试
    private class DoubleNode<E>{
        E data;
        DoubleNode<E> pre;
        DoubleNode<E> next;

        public DoubleNode() {}

        public DoubleNode(E data, DoubleNode<E> pre, DoubleNode<E> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
    }

    private DoubleNode<E> head = new DoubleNode<E>(null,null,null);

    public MyDoubleLinkList() {
        head.next = head.pre = head;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public void addBefore(E e, int index) {

    }

    @Override
    public void add(E e) {
        checkArgs(e);
        addBefore(e,head);
    }

    @Override
    public E set(E e, int index) {
        return null;
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyDoubleLinkList{");
        DoubleNode<E> p=head.next;
        while (head!=p){
            sb.append(p.data+", ");
            p = p.next;
        }
        sb.append("} ").append("size is ").append(size);
        return sb.toString();
    }

    private void addBefore(E e, DoubleNode<E> node){
        DoubleNode<E> newNode = new DoubleNode<E>(e,node.pre,node);
        newNode.pre.next = newNode;
        newNode.next.pre = newNode;
        size++;
    }
}
