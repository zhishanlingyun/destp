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

    public Tree<T> covertLink(Tree<T> root){
        if(null==root){
            return null;
        }
        Tree<T> last = process(root);
        root = last.right;
        last.right = null;
        return root;
    }

    public Tree<T> process(Tree<T> head){
        if(null==head){
            return null;
        }
        Tree<T> leftE = process(head.lift);
        Tree<T> rightE = process(head.right);
        Tree<T> leftS = leftE!=null?leftE.right:null;
        Tree<T> rightS = rightE!=null?rightE.right:null;
        if(leftE!=null&&rightE!=null){
            leftE.right = head;
            head.lift = leftE;
            head.right = rightS;
            rightS.lift = head;
            rightE.right = leftS;
            return rightE;
        }else if(leftE!=null){
            leftE.right = head;
            head.lift = leftE;
            head.right = leftS;
            return head;
        }else if(rightE!=null){
            head.right = rightS;
            rightS.lift = head;
            rightE.right = head;
            return rightE;
        }else {
            head.right = head;
            return head;
        }
    }


}
