package com.destp.acm.link;

import org.springframework.util.Assert;

/**
 * Created by zsly on 17-11-1.
 * 简单循环单链表
 */
public class Linked<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public Linked() {
        head = new Node<T>();
        tail = head;
        head.next = tail;
        tail.next = head;
    }

    public void add(T data){
        Assert.notNull(data,"数据不能为空");
        /*Node<T> node = new Node<T>(data);
        node.next = tail.next;
        tail.next = node;
        tail = node;
        size++;*/
        addAfter(tail,data);
    }

    private void addAfter(Node<T> node,T data){
        Assert.notNull(node);
        Node newNode = new Node(data);
        newNode.next = node.next;
        node.next = newNode;
        tail = newNode;
        size++;
    }

    public T peekLast(){
        T data = null;
        if(head!=tail){
            data = tail.data;
        }
        return data;
    }

    public T removeFrist(){
        T data = null;
        if(head!=tail){
            Node<T> node = head.next;
            head.next = node.next;
            data = node.data;
            node = null;
            size--;
        }
        return data;
    }

    public T peekFrist(){
        if(head!=tail){
            return head.next.data;
        }
        return null;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public Node<T> getHead(){
        return head;
    }

    public Node<T> getTail(){
        return tail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Linked{} [head]");
        Node p = head.next;
        while (p!=head){
            sb.append(p.data).append(",");
            p = p.next;

        }
        return sb.toString();
    }

    public void reverse(){
        if(isEmpty()||size<2){
            return;
        }
        Node p = head;
        Node q = p.next;
        while (q!=head){
            head.next = q.next;
            q.next = p;
            p = q;
            q = head.next;
        }
        head.next = p;
    }

    class Node<T>{
        T data;
        Node<T> next;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

}
