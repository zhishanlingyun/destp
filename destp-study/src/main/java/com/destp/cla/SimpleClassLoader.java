package com.destp.cla;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
public class SimpleClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File("E:\\data\\OutTest.class"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] b = null;
        try {
            b = new byte[in.available()];
            in.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return defineClass(name,b,0,b.length);
    }
}
