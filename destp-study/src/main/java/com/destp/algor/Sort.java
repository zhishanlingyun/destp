package com.destp.algor;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-11-14
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */
public class Sort extends Algor{

    /**
     * @param a
     */
    public static void quicksort(int[] a){
        quicksort0(0,a.length-1,a);
    }

    private static void quicksort0(int left,int right,int[] a){
        int temp=a[left];
        int i=left,j=right;
        //递归结束，返回
        if(left>right){
            return;
        }
        while(i!=j){
            //从后向前搜索，找到小与目标值停止，顺序很重要
            while(a[j]>=temp && i<j) j--;
            //从前向后搜索，找到大于目标值停止
            while(a[i]<=temp && i<j) i++;
            if(i<j){
                //左右搜索哨兵未相遇，则交换位置
                swap(i,j,a);
            }

        }
        //左右搜索相遇,即找第K位置，与目标值交换，完成一次寻找K
        swap(left,i,a);
        quicksort0(left,i-1,a);
        quicksort0(i+1,right-1,a);
    }

    public static void heapSort(int[] a){
        int[] heap = createHeap(a);
        for(int i=0;i<=a.length;i++){
            System.out.print(deleteMax(heap)+", ");
        }
    }


}
