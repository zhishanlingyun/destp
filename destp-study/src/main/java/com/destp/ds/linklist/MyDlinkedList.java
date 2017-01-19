package com.destp.ds.linklist;

import com.destp.ds.DList;
import com.destp.ds.IList;

/**
 * 双向循环链表
 */
public class MyDlinkedList<E> implements IList<E>,DList {

    private class Node{
        E element;
        Node previous;
        Node next;

        public Node(E element, Node previous, Node next) {
            this.element = element;
            this.previous = previous;
            this.next = next;
        }
    }

    private Node head = new Node(null,null,null);

    private int size;

    public MyDlinkedList() {
        head.next = head.previous = head;
    }

    private void addBefore(E e, Node node){
        Node newNode = new Node(e,node.previous,node);
        newNode.previous.next = newNode;
        newNode.next.previous = newNode;
        size++;
    }

    public void add(E e) {
        addBefore(e,head);
    }

    public E get(int i) {
        rangeCheck(i);
        if(i < (size >> 1)){
            //靠头，则从头找
            int index=0;
            Node p = head;
            while(index<=i){
                p = p.next;
                index++;
            }
            return p.element;
        }else {
            //靠尾，从尾找
            int index=size-1;
            Node p = head.previous;
            while (index>i){
                p = p.previous;
                index--;
            }
            return p.element;
        }
    }

    public boolean remove(E e) {
        if(e==head){
            throw new IllegalArgumentException("can not remove head !");
        }
        Node p = head.next;
        while (p!=head){
            if(p.element.equals(e)){
                Node r = p;
                p.previous.next = p.next;
                p.next.previous = p.previous;
                r = null;
                size--;
                return true;
            }
            p = p.next;
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
        int index=0;
        while (p!=head){
            if(p.element.equals(e)){
                return index;
            }
            p = p.next;
            index++;
        }
        return -1;
    }

    public void clear() {
        Node p = head.next;
        while (p!=head){
            Node r = p;
            p.previous.next = p.next;
            p.next.previous = p.previous;
            r = null;
            size--;
            p = p.next;
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
        StringBuilder sb = new StringBuilder("MyDlinkedList{ ");
        Node p = head.next;
        while(head!=p){
            sb.append(p.element).append(", ");
            p = p.next;
        }
        sb.append(" } size is ").append(size);
        return sb.toString();
    }
}
