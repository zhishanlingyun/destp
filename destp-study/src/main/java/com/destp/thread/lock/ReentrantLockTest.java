package com.destp.thread.lock;

import com.destp.common.CommonUtil;
import com.destp.thread.ObjectValue;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * ReentrantLock 重入锁，显示锁
 * Lock抽象了锁的操作，与内部锁不同，lock提供了无条件的，可定时的，可轮询的，可中断的锁获取操作
 * 内部锁在1.6以后性能与显示锁相差无几，但是内部锁不能中断那些正在等待获取锁的线程，在获取所失败情况下，只能无限等待下去
 * 默认创建一个非公平锁
 * 公平锁：在多个线程等待获取锁时，按照FIFO顺序获取锁
 * 非公平锁：在多个线程等待获取锁时，按照线程优先级以抢占式获取锁
 * 使用场景：
 * 1.可以取消等待获得锁的线程
 * 2.如果发现该操作已经在执行，则尝试等待一段时间，等待超时则不执行
 * 3.如果发现该操作已经在执行，等待一个一个执行（同步执行，类似synchronized）
 */
public class ReentrantLockTest {

    private static final Logger log = Logger.getLogger(ReentrantLockTest.class);

    //本例展示了内部锁无法响应中断请求，只能一直尝试获取锁
    public void notinterrupted(){
        Object lock = new Object();
        ObjectValue value = new ObjectValue();
        Thread w = new Thread(new WriteRun(lock,value));
        Thread r = new Thread(new ReadRun(lock,value));
        w.start();
        r.start();
        CommonUtil.sleep(5);
        log.info("main 中断读取线程...");
        w.interrupt();
        log.info(r.getName() + "\t"+ r.isInterrupted());
    }

    public void caninterrupted(){
        Lock lock = new ReentrantLock();
        ObjectValue value = new ObjectValue();
        Thread w = new Thread(new WriteInterruptedRun(lock,value));
        Thread r = new Thread(new ReadInterruptedRun(lock,value));
        w.setName("写线程");
        r.setName("读线程");
        w.start();
        r.start();
        CommonUtil.sleep(2);
        log.info("main 中断读取线程...");
        r.interrupt();
        log.info(r.getName() + "\t"+ r.isInterrupted());
    }

    public static void main(String[] args){
        ReentrantLockTest rt = new ReentrantLockTest();
        rt.notinterrupted();
        //rt.caninterrupted();
    }



    public class ReadRun implements Runnable{
        private Object lock;
        private ObjectValue value;

        public ReadRun(Object lock,ObjectValue value) {
            this.lock = lock;
            this.value = value;
        }

        public void run() {
            CommonUtil.sleep(1);
            log.info("ReadRun 尝试获取锁 ...");
            synchronized(lock){
                log.info("ReadRun 已经获取锁 ...");
                log.info("ReadRun 读取值 "+value.getValue());
            }
            log.info("ReadRun 释放锁 ...");
        }
    }

    public class WriteRun implements Runnable{
        private Object lock;
        private ObjectValue value;

        public WriteRun(Object lock,ObjectValue value) {
            this.lock = lock;
            this.value = value;
        }

        public void run() {
            log.info("WriteRun 尝试获取锁 ...");
            synchronized(lock){
                log.info("WriteRun 已经获取锁 ...");
                int n = CommonUtil.getRodam(0,10);
                CommonUtil.sleep(200);
                CommonUtil.sleep(200);
                value.setValue(String.valueOf(n));
                log.info("WriteRun 设置值 "+n);
            }
            log.info("WriteRun 释放锁 ...");
        }
    }

    class ReadInterruptedRun implements Runnable{
        private Lock lock;
        private ObjectValue value;

        public ReadInterruptedRun(Lock lock, ObjectValue value) {
            this.lock = lock;
            this.value = value;
        }

        public void read1(){
            log.info("ReadInterruptedRun 尝试获取锁 ...");
            try {
                //该方法调用了Thread.interrupted()方法，会清除中断状态
                lock.lockInterruptibly();

            } catch (InterruptedException e) {
                log.info("ReadInterruptedRun 被中断放弃获取锁等待...");
                log.info(Thread.currentThread().getName()+"\t"+Thread.currentThread().isInterrupted());
                return;
            }
            try {
                log.info("ReadInterruptedRun 已经获取锁 ...");
                log.info("ReadInterruptedRun 读取值 "+value.getValue());
            }finally {
                lock.unlock();
                log.info("ReadInterruptedRun 释放锁 ...");
            }
            log.info("ReadInterruptedRun end");
        }

        //或者使用tryLock(time)
        public void read2(){
            log.info("ReadInterruptedRun 尝试获取锁 ...");
            try {
                if(lock.tryLock(3,TimeUnit.SECONDS)){
                    try {
                        log.info("ReadInterruptedRun 已经获取锁 ...");
                        log.info("ReadInterruptedRun 读取值 "+value.getValue());
                    }finally {
                        lock.unlock();
                        log.info("ReadInterruptedRun 释放锁 ...");
                    }
                }else {
                    log.info("ReadInterruptedRun 等待获取锁超时");
                }
            } catch (InterruptedException e) {
                log.info("ReadInterruptedRun 被中断放弃获取锁等待...");
            }
            log.info("ReadInterruptedRun end");
        }

        public void run() {
            CommonUtil.sleep(1);
            read2();
        }
    }

    class WriteInterruptedRun implements Runnable{
        private Lock lock;
        private ObjectValue value;

        public WriteInterruptedRun(Lock lock, ObjectValue value) {
            this.lock = lock;
            this.value = value;
        }

        public void run() {
            log.info("WriteInterruptedRun 尝试获取锁 ...");
            lock.lock();
            try {
                log.info("WriteInterruptedRun 已经获取锁 ...");
                int n = CommonUtil.getRodam(0,10);
                CommonUtil.sleep(20);
                value.setValue(String.valueOf(n));
                log.info("WriteInterruptedRun 设置值 "+n);
            } finally {
                lock.unlock();
                log.info("WriteInterruptedRun 释放锁 ...");
            }
        }
    }



}
