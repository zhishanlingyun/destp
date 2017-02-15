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
        int i=left,j=right-1;
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
        quicksort0(i+1,right,a);
    }

    public static void heapSort(int[] a){
        int[] heap = createHeap(a);
        for(int i=0;i<=a.length;i++){
            System.out.print(deleteMax(heap)+", ");
        }
    }

    public static void insertsort(int[] array){
        int temp=0;
        int j=0;
        for(int i=1;i<array.length;i++){
            temp=array[i];
            for(j=i;j>0&&array[j]<array[j-1];j--){
                swap(j,j-1,array);
            }
            array[j]=temp;
        }

    }

    public static void insertSort(int[] array){
        if(null==array||array.length<2) return;
        int tmp=0;
        int j=0;
        for(int p=1;p<array.length;p++){
            tmp = array[p];
            for(j=p;j>0&&tmp<array[j-1];j--){
                swap(j,j-1,array);
            }
            array[j] = tmp;
        }
    }

    public static void insertEasySort(int[] array){
        if(null==array||array.length<2) return;
        for(int i=0;i<array.length;i++){
            for(int j=i;j>0&&array[j]<array[j-1];j--){
                swap(j,j-1,array);
            }
        }
    }

    public static void bubbleSort(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<(array.length-i-1);j++){
                if(array[j]>array[j+1]){
                    swap(j,j+1,array);
                }
            }
        }
    }

    public static void quckSort(int[] array){
        qucksort(array,0,array.length-1);
    }

    private static void qucksort(int[] array,int left,int right){
        if(left>right){
            return;
        }
        int temp = array[left];
        int i=left,j=right;

        while (i!=j){
            while (temp<=array[j]&&i<j){
                j--;
            }
            while (temp>=array[i]&&i<j){
                i++;
            }
            if(i<j){
                swap(i,j,array);
            }
        }
        //swap(left,temp,array);
        array[left]=array[i];
        array[i]=temp;
        qucksort(array,left,i-1);
        qucksort(array,i+1,right);

    }



}
