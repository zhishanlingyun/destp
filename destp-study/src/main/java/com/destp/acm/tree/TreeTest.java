package com.destp.acm.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsly on 17-11-6.
 */
public class TreeTest {

    @Test
    public void create(){
        //Integer[] nodes = {1,2,9,5,7,6,15,20};
        Integer[] nodes = {6,2,7};
        BinTree<Integer> binTree = new BinTree<Integer>();
        Tree<Integer> root = binTree.createTree(nodes);
        System.out.println(root);
        List<Integer> visit1 = new ArrayList<Integer>();
        binTree.preVisit(root,visit1);
        System.out.println(visit1);
        List<Integer> visit2 = new ArrayList<Integer>();
        binTree.midVisit(root,visit2);
        System.out.println(visit2);
        List<Integer> visit3 = new ArrayList<Integer>();
        binTree.postVisit(root,visit3);
        System.out.println(visit3);
        final List<Integer> level = new ArrayList<Integer>();
        binTree.levelVisit(root, new Visit<Integer>() {
            @Override
            public void onVisit(Tree<Integer> node) {
                System.out.println(node.data);
                level.add(node.data);
            }
        });
        System.out.println(level);
        binTree.covertLink(root);
        //System.out.println(root);

    }



}
