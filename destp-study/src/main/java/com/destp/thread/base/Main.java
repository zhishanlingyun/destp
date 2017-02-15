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

    public void demo3(){

        SimpleThread t = new SimpleThread("simple-state-thread", new Runnable() {
            @Override
            public void run() {
                System.out.println("1-[ "+Thread.currentThread().getName()+" ]"+"state "+Thread.currentThread().isAlive());
                CommonUtil.sleep(5);
            }
        });
        t.start();
        System.out.println("2-[ "+t.getName()+" ]"+"state "+t.isAlive());
        CommonUtil.sleep(10);
        System.out.println("3-[ "+t.getName()+" ]"+"state "+t.isAlive());
        System.out.println("5-[ "+Thread.currentThread().getName()+" ]"+"state "+Thread.currentThread().isAlive());
    }

    public void demo5(){
        SimpleThread t = new SimpleThread("simple-state-thread", new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        if(Thread.currentThread().isInterrupted()){
                            throw new InterruptedException();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("是否被中断--- "+Thread.currentThread().isInterrupted());
                    System.out.println("是否被中断--- "+Thread.interrupted());
                    System.out.println("是否被中断--- "+Thread.interrupted());
                    System.out.println("是否被中断--- "+Thread.currentThread().isInterrupted());
                }
            }
        });
        t.start();
        CommonUtil.sleep(5);
        t.interrupt();
        CommonUtil.sleep(5);
        System.out.println(t.interrupted());
        System.out.println(Thread.interrupted());
        System.out.println(t.isInterrupted());
    }

    public static void demo6(){
        final SimpleThread t = new SimpleThread("simple-state-thread", new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (true){
                    System.out.println(i++);
                    CommonUtil.sleep(1);
                }
            }
        });
        t.start();
        CommonUtil.sleep(5);
        t.suspend();
        //CommonUtil.sleep(5);
        //t.resume();
    }

    public static void main(String[] args) {
        //demo1();
        new Main().demo6();
    }

    /**
     *使用synchronized 监视器(this),展示了一个方法被调用，会阻塞其他synchronized的方法调用
     */
    class AddService{
        private int n;

        public synchronized void add(){
            System.out.println("n = "+n);
            n++;
            CommonUtil.sleep(5);
            //E:\jd\workerspace\jsf\mvn-jsf\jsf-index-server
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
