package com.destp.acm.tree;

import com.destp.acm.link.Linked;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zsly on 17-11-6.
 * 二叉搜索树
 */
public class BinTree<T extends Comparable> {

    //
    public Tree<T> createTree(T[] ts){
        Tree<T> root = null;
        if(null!=ts&&ts.length>0){
            for(T dat : ts){
                root = insert(root,dat);
            }
        }
        return root;
    }

    public Tree<T> insert(Tree<T> node,T data){
        if(null==node){
            node = new Tree(data);
            node.lift = null;
            node.right = null;
            System.out.println("create node is "+node);
            node.childsiez=0;
            return node;
        }
        node.childsiez +=1;
        if(data.compareTo(node.data)<=0){
            node.lift = insert(node.lift,data);
        }else {
            node.right =  insert(node.right,data);
        }
        return node;
    }

    public void preVisit(Tree<T> root, List<T> list){
        if(root==null){
            return;
        }
        System.out.println(root.data);
        list.add(root.data);
        preVisit(root.lift,list);
        preVisit(root.right,list);
    }

    public void midVisit(Tree<T> root,List<T> list){
        if(null==root){
            return;
        }
        midVisit(root.lift,list);
        System.out.println(root.data);
        list.add(root.data);
        midVisit(root.right,list);
    }

    public void postVisit(Tree<T> root,List<T> list){
        if(null==root){
            return;
        }
        postVisit(root.lift,list);
        postVisit(root.right,list);
        System.out.println(root.data);
        list.add(root.data);
    }

    public void levelVisit(Tree<T> root,Visit<T> visit){
        if(null==root){
            return;
        }
        LinkedList<Tree<T>> list = new LinkedList<Tree<T>>();
        list.add(root);
        while (!list.isEmpty()){
            Tree<T> node = list.poll();
            visit.onVisit(node);
            if(null!=node.lift){
                list.add(node.lift);
            }
            if(null!=node.right){
                list.add(node.right);
            }
        }
    }



}
