package com.destp.thread.communicate;

import com.destp.common.CommonUtil;

/**
 * 解决每个线程绑定自己的值，可以将ThreadLocal类比喻成全局存放的数据盒子，盒子中可以存储每个线程的私有数据
 * 每次get 拿前一次值，如果前一次值是最后一次更新值，则以后只能那最后一次的值
 */
public class ThreadLocalTest {

    public static ThreadLocal<String> tl = new ThreadLocal<String>();

    public void demo(){
        tl.set("aaa");
        tl.set("bbb");
        System.out.println("main - "+tl.get());
        MyThread t1 = new MyThread();
        t1.setName("mythread1");
        MyThread t2 = new MyThread();
        t2.setName("mythread2");
        t1.start();
        CommonUtil.join(t1);
        t2.start();
        CommonUtil.join(t2);
        System.out.println("main - "+tl.get());
        System.out.println("main - "+tl.get());
        System.out.println("main - "+tl.get());


    }

    public static void main(String[] args){
        ThreadLocalTest tt = new ThreadLocalTest();
        tt.demo();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<3;i++){
                tl.set(this.getName()+"-"+i);
                System.out.println(tl.get());
                CommonUtil.sleep(1);
            }
            System.out.println(this.getName()+" ---------------- "+tl.get());
        }
    }

}
