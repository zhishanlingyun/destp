package com.destp.thread.communicate;

import com.destp.common.CommonUtil;

/**
 * join()的作用是等待线程对象销毁，即使所属线程对象x正常执行run()方法，二使当前线程z进行无限期的阻塞，等待线程x销毁后再继续执行
 * 线程z后面的代码。
 * 方法join 具有使线程排队运行的作用，类似同步的运行效果。
 * join与synchronized的区别是：join在内部使用wait()方法进行等待;synchronized使用的是“对象监视器”原理作为同步;
 * join(long) 内部实现是通过wait() 所以会释放锁
 */
public class JoinTest {

    public void nojoin(){
        RandomThread t = new RandomThread();
        t.setName("RandomThread");
        t.start();
        System.out.println("main get sleep is ");
    }

    public void usejoin(){
        RandomThread t = new RandomThread();
        t.setName("RandomThread");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main get sleep is ");
    }

    public static void main(String[] args){
        JoinTest jt = new JoinTest();
        //jt.nojoin();
        jt.usejoin();
    }

    class RandomThread extends Thread{

        @Override
        public void run() {
            int sec = CommonUtil.getRodam(1,10);
            System.out.println(this.getName()+" 休眠 "+sec);
            CommonUtil.sleep(sec);
        }
    }

}
