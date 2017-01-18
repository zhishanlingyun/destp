package com.destp.thread.communicate;

import com.destp.common.CommonUtil;
import com.destp.thread.SimpleThread;

/**
 * 本类列出wait和notify/notifyAll的基本用法
 * 要点有：
 * wait()的作用是使当前执行代码的线程进行等待。在执行前必须获得该对象的对象锁；在执行后，当前线程释放锁
 * notify()用来通知等待该对象的对象锁的其他线程，如果有多个线程等待，则由线程规划器随机挑选其中一个呈wait状态，对其通知。
 * 执行之前必须获得该对象的对象锁；执行后，当前线程不会立刻释放锁，而是在执行完同步代码块后，释放锁
 * 总结：wait使线程停止运行，notify使停止的线程继续运行
 * 1.wait() notify()时，线程必须获得该对象级别的锁，否则会抛出IllegalMonitorStateException
 * 线程状态：
 * TIMED_WAITING 等待时间钟 调用sleep()方法 waiting on condition
 * BLOCKED 等待锁 waiting for monitor entry
 *
 */
public class SimpleTest {

    /**
     * 本例说明在使用wait() notify时，线程必须获得该对象级别的锁，否则会抛出IllegalMonitorStateException
     */
    public void nolock(){
        Object obj = new Object();
        obj.notify();
        try {
            obj.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //本例说明可以使用wait notify来控制线程的启动和暂停
    public void threadState(){
        Object lock = new Object();
        SimpleThread t = new SimpleThread(lock);
        t.setName("SimpleThread线程");
        t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.setState(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock){
            t.setState(false);
            lock.notify();
        }

    }

    //唤醒一个或全部线程
    public void notifyOne(){
        Object lock = new Object();
        final  SimpleThread s1 = new SimpleThread(lock,"s1");
        final SimpleThread s2 = new SimpleThread(lock,"s2");
        final SimpleThread s3 = new SimpleThread(lock,"s3");
        s1.start();
        s2.start();
        s3.start();
        CommonUtil.sleep(5);
        s1.setState(true);
        s2.setState(true);
        s3.setState(true);
        CommonUtil.sleep(5);
        CommonUtil.notifyAll(lock, new CommonUtil.Fun() {
            public void run() {
                s1.setState(false);
                s2.setState(false);
                s3.setState(false);
            }
        });
    }


    public void lock(){
        final LockWaitService lks = new LockWaitService();
        Thread t1 = new com.destp.thread.SimpleThread("锁定线程", new Runnable() {
            public void run() {
                lks.lock();
            }
        });
        Thread t2 = new com.destp.thread.SimpleThread("获取锁线程", new Runnable() {
            public void run() {
                lks.trylock();
            }
        });
        t1.start();
        CommonUtil.sleep(1);
        t2.start();

    }



    public static void main(String[] args){
        SimpleTest st = new SimpleTest();
        //st.nolock();
        //st.threadState();
        //st.notifyOne();
        st.lock();
    }

    class SimpleThread extends Thread{

        private Object lock;

        private boolean state=false;

        public SimpleThread(Object lock) {
            this.lock = lock;
        }

        public SimpleThread(Object lock,String name){
            this.lock = lock;
            setName(name);
        }

        public synchronized void setState(boolean state){
            this.state = state;
        }

        @Override
        public void run() {
            int n=0;
            while(true){
                if(Boolean.TRUE.equals(state)){
                    synchronized (lock){
                        try {
                            System.out.println("name : "+this.getName()+"curThreadName : "+Thread.currentThread().getName()+" 停止");
                            lock.wait();
                            System.out.println("name : "+this.getName()+"curThreadName : "+Thread.currentThread().getName()+" 启动");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("name : "+this.getName()+"curThreadName : "+Thread.currentThread().getName()+" "+n++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class LockWaitService{
        private Object lock = new Object();

        public void lock(){
            System.out.println(Thread.currentThread().getName()+"\t"+"尝试获取锁...");
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"\t"+"锁定...");
                //不释放锁，观察等待锁释放线程的状态
                CommonUtil.sleep(120);
            }
        }

        public void trylock(){
            System.out.println(Thread.currentThread().getName()+"\t"+"尝试获取锁...");
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"\t"+"锁定...");
            }
        }

    }

}
