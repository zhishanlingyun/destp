package com.destp.thread.lock;

import com.destp.common.CommonUtil;
import com.destp.thread.ReadWriteService;
import com.destp.thread.SimpleThread;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * 读写锁
 * 此类具有以下属性：
 * 1.获取顺序：默认采用非公平锁，可以支持公平锁
 * 2.重入：此锁允许 reader 和 writer 按照 ReentrantLock 的样式重新获取读取锁或写入锁。
 *   在写入线程保持的所有写入锁都已经释放后，才允许重入 reader 使用它们。
 *   此外，writer 可以获取读取锁，但反过来则不成立。在其他应用程序中，
 *   当在调用或回调那些在读取锁状态下执行读取操作的方法期间保持写入锁时，
 *   重入很有用。如果 reader 试图获取写入锁，那么将永远不会获得成功。
 * 3.锁降级：重入还允许从写入锁降级为读取锁，其实现方式是：先获取写入锁，然后获取读取锁，最后释放写入锁。
 *   但是，从读取锁升级到写入锁是不可能的。
 * 4.锁获取的中断
 * 5.Condition 支持
 * 6.监测
 *
 */
public class ReetrantReadWriteLockTest {

    private static final Logger log = Logger.getLogger(ReetrantReadWriteLockTest.class);

    //读读共享锁
    public void read(){
        final ReadWriteService service = new ReadWriteLockService();
        Thread r1 = new SimpleThread("r1", new Runnable() {
            public void run() {
                service.read();
            }
        });
        Thread r2 = new SimpleThread("r2", new Runnable() {
            public void run() {
                service.read();
            }
        });
        r1.start();
        r2.start();
    }

    public void readmux(){
        final ReadWrite service = new ReadWrite();
        Thread r1 = new SimpleThread("排它锁-r1", new Runnable() {
            public void run() {
                service.read();
            }
        });
        Thread r2 = new SimpleThread("排它锁-r2", new Runnable() {
            public void run() {
                service.read();
            }
        });
        r1.start();
        r2.start();
    }

    public void rw(){
        final ReadWriteService service = new ReadWriteLockService();
        Thread r1 = new SimpleThread("r1", new Runnable() {
            public void run() {
                while (true)
                service.read();
            }
        });
        Thread r2 = new SimpleThread("r2", new Runnable() {
            public void run() {
                while (true)
                service.read();
            }
        });
        final Thread w1 = new SimpleThread("w1", new Runnable() {
            public void run() {
                while (true)
                service.write();
            }
        });
        r1.start();
        r2.start();
        w1.start();
    }

    public static void main(String[] args) {
        ReetrantReadWriteLockTest rt = new ReetrantReadWriteLockTest();
        //rt.read();
        //rt.readmux();
        rt.rw();
    }

    class ReadWriteLockService implements ReadWriteService{

        private ReadWriteLock rwlock = new ReentrantReadWriteLock();

        private String value = "abc";

        public void read() {
            log.info(Thread.currentThread().getName()+"\t"+"尝试获取读锁...");
            rwlock.readLock().lock();
            try {
                log.info(Thread.currentThread().getName()+"\t"+"已获取读锁...");
                log.info(Thread.currentThread().getName()+"\t"+"value : "+value);
                CommonUtil.sleep(1);
            } finally {
                rwlock.readLock().unlock();
                log.info(Thread.currentThread().getName()+"\t"+"释放读锁...");
            }

        }

        public void write() {
            log.info(Thread.currentThread().getName()+"\t"+"尝试获取写锁...");
            rwlock.writeLock().lock();
            try {
                log.info(Thread.currentThread().getName()+"\t"+"已获取写锁...");
                value = CommonUtil.getRodam(0,10)+"";
                log.info(Thread.currentThread().getName()+"\t"+"value : "+value);
                CommonUtil.sleep(5);
            } finally {
                rwlock.writeLock().unlock();
            }

        }
    }

    class ReadWrite implements ReadWriteService{
        private Lock lock = new ReentrantLock();
        private String value = "abcdef";
        public void read() {
            log.info(Thread.currentThread().getName()+"\t"+"尝试获取锁...");
            lock.lock();
            try {
                log.info(Thread.currentThread().getName()+"\t"+"已获取锁...");
                log.info(Thread.currentThread().getName()+"\t"+"value : "+value);
                CommonUtil.sleep(1);
            } finally {
                lock.unlock();
                log.info(Thread.currentThread().getName()+"\t"+"释放锁...");
            }
        }

        public void write() {

        }
    }
}
