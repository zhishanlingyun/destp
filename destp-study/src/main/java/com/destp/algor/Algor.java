package com.destp.algor;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-11-14
 * Time: 下午6:26
 * To change this template use File | Settings | File Templates.
 */
public class Algor {

    protected static void swap(int x,int y,int[] a){
        int t = 0;
        t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    public static void printArray(int[] a){
        for(int t:a){
            System.out.print(t+" ,");
        }
        System.out.println();
    }

    /**
     * heap adjust from boom to top
     * 堆特性: 若父节点为k,则左孩子为2*k,右孩子为2*k+1;
     * 一颗完全二叉树有N个节点，那么这个完全二叉树的高度为logN
     * 最后一个非叶子节点为N/2
     * 小顶堆
     * 从i节点向下调整
     */
    public static void heapSiftDown(int i,int[] heap){
        int flag=0; //标记，用来标记是否需要继续向下调整
        int n = heap.length-1; //堆大小
        int t = 0; //记录移动位置
        while((2*i<=n) && (flag==0)){  //至少存在左孩子,并且未停止
            //选在i节点中两个孩子较小值交换
            if(heap[i]>heap[2*i]){   //先与左孩子比较
                t = 2*i;
            }else {
                t = i;
            }
            //判断是否存在右孩子，有则比较之
            if(2*i+1<=n){
                //如果右儿子更小，则更新较小的节点编号
                if(heap[t]>heap[2*i+1]){
                    t = 2*i+1;
                }
            }
            //如果发现最小的节点编号不是自己，则交换父子节点
            if(t!=i){
                swap(t,i,heap);
                i = t;        //更新i为刚才与它交换的编号，继续向下调整
            }else{
                flag = 1;     //说明父节点比两个孩子节点小，不需要继续向下调整
            }
        }
    }

    public static void heapSiftUp(int i,int[] heap){
        int flag = 0;
        if(i==0) return;
        while(i!=0 && flag==0){
            if(heap[i]<heap[i/2]){
                swap(i,i/2,heap);
            }else {
                flag=1;
            }
            i=i/2;
        }
    }

    /**
     * 该建堆方法时间复杂度为O(N)
     * 思想:先将数据按照从上到下，从左到右装入完全二叉树组中，从最后一个节点开始，依次判断以该节点为根的子树是否符合小顶堆
     * 特性。如果所有子树都符合小顶堆特性，那么整个树就是小顶堆了
     */
    public static int[] createHeap(int[] a){
        int[] heap = Arrays.copyOf(a,a.length);
        printArray(heap);
        int n = a.length;
        for(int i=n/2;i>=0;i--){
            heapSiftDown(i,heap);
        }
         return heap;
    }

    public static int deleteMax(int[] heap){
        int t=0;
        int n = heap.length-1;
        t = heap[0];
        heap[0] = heap[n];
        n--;
        heapSiftDown(0,heap);
        return t;
    }
}
