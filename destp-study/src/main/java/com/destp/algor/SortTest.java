package com.destp.algor;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-11-14
 * Time: 下午6:53
 * To change this template use File | Settings | File Templates.
 */
public class SortTest {

    public static void quickSort(){
        //int[] a = {2,6,3,2,1,7,9,200,110,50};
        int[] a = {99,5,36,7,22,17,46,12,2,19,25,28,1,92};
        Sort.printArray(a);
        Sort.quicksort(a);
        Sort.printArray(a);
    }


    public static void createHeap(){
        int[] a = {99,5,36,7,22,17,46,12,2,19,25,28,1,92};
        int[] heap = Sort.createHeap(a);
        Sort.printArray(heap);
    }

    public static void heapsort(){
        int[] a = {99,5,36,7,22,17,46,12,2,19,25,28,1,92};
        Sort.heapSort(a);
    }

    public static void insertSort(){
        int[] a = {99,5,36,7,22,17,46,12,2,19,25,28,1,92};
        //Sort.insertSort(a);
        //Sort.insertEasySort(a);
        Sort.insertsort(a);
        Sort.printArray(a);
    }

    public static void bubbleSort(){
        int[] a = {99,5,36,7,22,17,46,12,2,19,25,28,1,92};
        Sort.bubbleSort(a);
        Sort.printArray(a);
    }

    public static void quckSort(){
        int[] a = {99,5,36,7,22,17,46,12,2,19,25,28,1,92};
        Sort.quckSort(a);
        Sort.printArray(a);
    }


    public static void main(String[] args){
        //quickSort();
        //createHeap();
        //heapsort();
        quckSort();
    }
}
