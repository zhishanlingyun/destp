package com.destp.common;

import java.util.Random;

/**
 * Created by Administrator on 2016/12/31 0031.
 */
public class CommonUtil {

    public static void sleep(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void notify(Object lock){
        synchronized (lock){
            lock.notify();
        }
    }

    public static void notify(Object lock,Fun fun){
        synchronized (lock){
            fun.run();
            lock.notify();
        }
    }

    public static void notifyAll(Object lock,Fun fun){
        synchronized (lock){
            fun.run();
            lock.notifyAll();
        }
    }

    public static int getRodam(int min,int max){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }

    public static void join(Thread t){
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface Fun{
        void run();
    }


}
