package com.destp.thread.communicate;

import com.destp.common.CommonUtil;
import com.sun.org.apache.xerces.internal.parsers.CachingParserPool;

/**
 * join()的作用是等待线程对象销毁，即使所属线程对象x正常执行run()方法，二使当前线程z进行无限期的阻塞，等待线程x销毁后再继续执行
 * 线程z后面的代码。
 * 方法join 具有使线程排队运行的作用，类似同步的运行效果。
 * join与synchronized的区别是：join在内部使用wait()方法进行等待;synchronized使用的是“对象监视器”原理作为同步;
 * join(long) 内部实现是通过wait() 所以会释放锁
 */
public class JoinTest {

    public void nojoin(){
        RandomThread t = new RandomThread("t");
        t.setName("RandomThread");
        t.start();
        System.out.println("main get sleep is ");
    }

    public void usejoin(){
        RandomThread t = new RandomThread("t");
        t.setName("RandomThread");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main get sleep is ");
    }

    public void mujoin(){
        RandomThread[] rts = new RandomThread[5];
        for(int i=0;i<5;i++){
            rts[i] = new RandomThread("t-"+i);
        }
        for(RandomThread t : rts){
            t.start();
        }
        for(RandomThread t : rts){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("fuccess");

    }

    public static void main(String[] args){
        JoinTest jt = new JoinTest();
        //jt.nojoin();
        //jt.usejoin();
        jt.mujoin();
    }

    class RandomThread extends Thread{

        public RandomThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for(int i=0;i<5;i++){
                //int sec = CommonUtil.getRodam(1,5);
                System.out.println(Thread.currentThread().getName()+" 休眠 "+1);
                CommonUtil.sleep(1);
            }

        }
    }

}
