package com.destp.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/2/11 0011.
 */
public class Test {

    public void demo1(){
        OutputStream out = null;
        try {
            out = new FileOutputStream("E:\\data\\f1.dat");
            out.write(60);
            out.flush();
            System.out.println("写入完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void printByte(byte b){
        System.out.println(b);
    }

    public void map(){
        //Map map = new ConcurrentHashMap();
        //Map map = new HashMap();
        //Map map = new LinkedHashMap();
        Map map = new TreeMap();
        map.put("1","a");
        map.put("3","c");
        map.put("2","b");
        System.out.println(map);
    }

    public void demo2(){
        try {
            ServerSocket server = new ServerSocket();
            server.bind(new InetSocketAddress("127.0.0.1",9090));
            server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Test().map();
        //new Test().printByte((byte)00);
    }


}
