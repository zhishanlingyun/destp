package com.destp.thread.communicate;

import com.destp.common.CommonUtil;
import com.destp.thread.SimpleThread;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 多路通知
 * 类似于内部锁synchronized 与 wait() notify()/notifyAll() 方法结合实现的等待/通知模式
 *
 *
 */
public class ConditionTest {
    public static final Logger log = Logger.getLogger(ConditionTest.class);

    //实现了线程的启动-暂停-启动
    public void notifyOK(){
        final Service service = new Service();
        Thread t = new SimpleThread("exe", new Runnable() {
            public void run() {
                int n = 0;
                log.info("任务开始执行...");
                while (true){
                    if(0==service.getState()){
                        log.info("执行第 "+(n++)+" 条任务...");
                        CommonUtil.sleep(1);
                    }else if(1==service.getState()){
                        log.info("任务暂停...");
                        service.pause();
                    }
                }
            }
        });
        t.start();
        CommonUtil.sleep(5);
        service.setState(1);
        CommonUtil.sleep(5);
        service.restart();
        CommonUtil.sleep(2);
        service.setState(1);
        CommonUtil.sleep(5);
        t.interrupt();
    }

    public void mulitue(){
        final EchoService echoService = new EchoService();
        Thread t1 = new SimpleThread("t1", new Runnable() {
            public void run() {
                echoService.m1();
            }
        });
        Thread t2 = new SimpleThread("t2", new Runnable() {
            public void run() {
                echoService.m2();
            }
        });
        t1.start();
        t2.start();
        CommonUtil.sleep(5);
        echoService.signalA();
    }

    public static void main(String[] args) {
        ConditionTest ct = new ConditionTest();
        //ct.notifyOK();
        ct.mulitue();
    }




    class Service {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private volatile int stop = 0; //0启动；1暂停；2重启

        public int getState(){
            return stop;
        }

        public void setState(int state){
            this.stop = state;
        }

        public void pause(){
            log.info(Thread.currentThread().getName()+" 尝试获取锁...");
            lock.lock();
            try {
                log.info(Thread.currentThread().getName()+" 获取锁成功...");
                log.info(Thread.currentThread().getName()+" 等待条件...");
                //抛出中断异常，并清除中断状态
                condition.await();
                log.info(Thread.currentThread().getName()+" 等待条件完成...");
            } catch (InterruptedException e) {
                log.info(Thread.currentThread().getName()+" 等待条件时被中断...");
                stop=0;
            } finally {
                lock.unlock();
                log.info(Thread.currentThread().getName()+" 解锁...");
            }
        }

        public void restart(){
            log.info(Thread.currentThread().getName()+" 尝试获取锁...");
            lock.lock();
            try {
                log.info(Thread.currentThread().getName()+" 获取锁成功...");
                stop = 0;
                condition.signal();
                log.info(Thread.currentThread().getName()+" 发送通知成功...");
            } finally {
                lock.unlock();
                log.info(Thread.currentThread().getName()+" 解锁...");
            }

        }

    }

    class EchoService{
        private Lock lock = new ReentrantLock();
        private Condition ca = lock.newCondition();
        private Condition cb = lock.newCondition();

        public void m1(){
            lock.lock();
            try {
                log.info(Thread.currentThread().getName()+" m1 执行...");
                log.info(Thread.currentThread().getName()+" m1 await...");
                ca.await();
                log.info(Thread.currentThread().getName()+" m1 await 完成...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void m2(){
            lock.lock();
            try {
                log.info(Thread.currentThread().getName()+" m2 执行...");
                log.info(Thread.currentThread().getName()+" m2 await...");
                cb.await();
                log.info(Thread.currentThread().getName()+" m2 await完成...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signalA(){
            lock.lock();
            try {
                ca.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void signalB(){
            lock.lock();
            try {
                cb.signalAll();
            } finally {
                lock.unlock();
            }
        }

    }
}
