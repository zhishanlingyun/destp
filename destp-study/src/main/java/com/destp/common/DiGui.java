package com.destp.common;

import jodd.util.StringUtil;

/**
 * Created by Administrator on 2017/2/13 0013.
 */
public class DiGui {

    //字符串反转-递归
    public String stringResever(String str){
        if(StringUtil.isEmpty(str)) return null;
        //return resver(str.toCharArray(),0,"");
        return resver(str);
    }

    private String resver(char[] array,int n,String s){
        if(n<array.length-1){
            s=resver(array,n+1,s);
        }
        return s+array[n];
    }

    private String resver(String str){
        String s="";
        for(int i=0;i<str.length();i++){
            s = str.charAt(i)+s;
        }
        return s;
    }



    public boolean isBackTxt(String str){
        return backtext(str,0,str.length());
    }

    private boolean backtext(String str,int left,int right){

        if(left>=right){
            return true;
        }

        if(str.charAt(left)!=str.charAt(right-1)){
            return false;
        }
        return backtext(str,left+1,right-1);
    }

    public static void main(String[] args) {
        String str = "abc";
        System.out.println(new DiGui().stringResever(str));
        //System.out.println(new DiGui().isBackTxt(str));
    }


}
