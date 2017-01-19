package com.destp.ds.linklist;

import com.destp.ds.IList;
import com.destp.ds.SList;

/**
 * 单链表
 */
public class MyLinkedList<E> implements IList<E>,SList {

    //单链表节点
    private class Node{
        E element;
        Node next;

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private int size;

    private Node head = new Node(null,null);

    private Node tail = head;

    private void addAfter(E e,Node node){
        if(null==node){
            return;
        }
        Node newNode = new Node(e,node.next);
        node.next = newNode;
        tail = newNode;
        size ++;
    }


    public void add(E e) {
        addAfter(e,tail);
    }

    public E get(int i) {
        rangeCheck(i);
        int index=0;
        Node p = head;
        while(index<=i){
            p = p.next;
            index++;
        }
        if(null!=p){
            return p.element;
        }
        return null;
    }

    public boolean remove(E e) {
        if(null==head.next) return false;
        Node tmp = head;
        while(null!=tmp.next){
            if(tmp.next.element.equals(e)){
                Node r = tmp.next;
                tmp.next = tmp.next.next;
                r = null;
                size--;
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public boolean contains(E e) {
        int n = indexOf(e);
        return n!=-1;
    }

    public int indexOf(E e) {
        Node p = head.next;
        int index = 0;
        while(null!=p){
            if(p.element.equals(e)){
                return index;
            }
            p = p.next;
            index++;
        }
        return -1;
    }

    public void clear() {
        Node p = head;
        while(null!=p.next){
            Node r = p.next;
            p.next = p.next.next;
            r = null;
            size--;
        }
    }

    public Iterable<E> iterable() {
        return null;
    }

    private void rangeCheck(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("index is "+index+" size is "+size);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyLinkedList{} ");
        Node p = head.next;
        while(null!=p){
            sb.append(p.element).append(", ");
            p = p.next;
        }
        sb.append(" size is "+size);
        return sb.toString();
    }
}

