package com.destp.thread.base;

import com.destp.common.CommonUtil;
import com.destp.thread.SimpleThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zsly on 17-2-8.
 */
public class Main {

    public static void demo1(){
        SimpleThread t = new SimpleThread("", new Runnable() {
            @Override
            public void run() {
                System.out.println("run ... ");
            }
        });
        t.start();
        CommonUtil.sleep(5);
        t.start();
    }

    public void demo2(){
        AddService3 addService = new AddService3();
        MyThread t = new MyThread("option-thread",addService);
        t.start();
        CommonUtil.sleep(1);
        while (true){
            System.out.println("尝试尝试等等read n ...");
            System.out.println(addService.getN());
            CommonUtil.sleep(1);
        }

    }

    public static void main(String[] args) {
        //demo1();
        new Main().demo2();
    }

    /**
     *
     */
    class AddService{
        private int n;

        public synchronized void add(){
            System.out.println("n = "+n);
            n++;
            CommonUtil.sleep(5);
        }

        public synchronized int getN(){
            return n;
        }
    }

    class AddService2{
        private Object lock = new Object();
        private int n;

        public void add(){
            synchronized (lock){
                n++;
                CommonUtil.sleep(5);
            }
        }

        public int getN(){
            synchronized (lock){
                return n;
            }
        }
    }

    class AddService3{
        private ReadWriteLock rwLock = new ReentrantReadWriteLock();
        private int n;

        public void add(){
            Lock lock = rwLock.writeLock();
            try {
                lock.tryLock();
                n++;
            } finally {
                lock.unlock();
            }
        }

        public int getN(){
            Lock lock = rwLock.readLock();
            try {
                lock.tryLock();
                return n;
            } finally {
                lock.unlock();
            }
        }
    }

    class MyThread extends Thread{

        private AddService3 addService;

        public MyThread(String name, AddService3 addService) {
            super(name);
            this.addService = addService;
        }

        @Override
        public void run() {
            while (true){
                addService.add();
                CommonUtil.sleep(5);
            }
        }
    }

}
