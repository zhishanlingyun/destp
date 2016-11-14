package service;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class ByteTest {

    public static void b1(){
        int a=-1;
        byte b = (byte)a;
        System.out.println(b);
        System.out.println(b&0xff);
    }

    public static void b2(){
        byte[] b = new byte[1024];
        String s = "abc";
        System.out.println(s.getBytes().length);
        //for()
        try {
            InputStream in = new ByteArrayInputStream(b);
            int r = in.read(s.getBytes());
            System.out.println(r);
            System.out.println(r&0xff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        b2();
    }

}
