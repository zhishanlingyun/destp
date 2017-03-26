package destp.exercise.e17217;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class Algo {

    protected boolean isBackTxt(String str){
        return backtxt(str,0,str.length()-1);
    }

    private boolean backtxt(String str,int left,int right){
        if(left>right){
            return true;
        }
        if(str.charAt(left)!=str.charAt(right)){
            return false;
        }
        return backtxt(str,left+1,right-1);
    }

    protected int[] merge(int[] a,int[] b){
        int[] r = new int[a.length+b.length];
        int i=0,j=0,k=0;
        while(i<a.length-1||j<b.length-1){
            if(a[i]<=b[j]){
                r[k++]=a[i++];
            }else {
                r[k++]=b[j++];
            }
        }
        while (i<a.length){
            r[k++]=a[i++];
        }
        while (j<b.length){
            r[k++]=b[j++];
        }
        return r;
    }

    private void mergeSort(int[] a,int left,int right){
        if(left<right){
            int mid = (right-left)>>2;
            mergeSort(a,left,mid);
            mergeSort(a,mid,right);
            //merge(a,)
        }
    }

    public static void main(String[] args) {
        Algo algo = new Algo();
        System.out.println(algo.isBackTxt("abckgba"));
        int[] a={6,8,23};
        int[] b={2,6,17,19,20};
        int[] m = algo.merge(a,b);
        System.out.println(Arrays.toString(m));
    }

}
