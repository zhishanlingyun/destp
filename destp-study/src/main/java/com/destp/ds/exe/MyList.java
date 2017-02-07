package com.destp.ds.exe;

/**
 * Created with IntelliJ IDEA.
 * User: zsly
 * Date: 17-2-7
 * Time: 下午3:51
 * To change this template use File | Settings | File Templates.
 */
public class MyList<E> extends BaseSet<E> implements List<E>,ISet<E> {

    private class Node<E>{
        E data;
        Node<E> next;

        private Node() {}

        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head = new Node<E>(null,null);
    private Node<E> tail = head;

    public MyList() {
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
    public void addBefore(Object o, int index) {
    }

    @Override
    public void add(E e) {
        addAfter(e,tail);
    }

    @Override
    public Object set(Object o, int index) {
        return null;
    }

    @Override
    public void remove(Object o) {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyList{");
        Node<E> p = head.next;
        while (null!=p){
            sb.append(p.data+", ");
            p = p.next;
        }
        sb.append("} size is ").append(size);
        return sb.toString();
    }

    private void addAfter(E e,Node<E> node){
        Node<E> newNode = new Node<E>(e,null);
        node.next = newNode;
        tail = newNode;
        size ++;
    }

    private int indexOf(E e){
        checkArgs(e);
        Node<E> p = head.next;
        int i=0;
        while (null!=p){
            if(e.equals(p.data)){
                return i;
            }
            p = p.next;
            i++;
        }
        return -1;
    }

    private E indexOf(int i){
        checkArgs(i);
        Node<E> p = head.next;
        int n=0;
        while (null!=p&&n<=i){
            n++;
            p = p.next;
        }
        if(i==n){
            return p.data;
        }
        return null;
    }
}
