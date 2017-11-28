package destp.net;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Run {
    Lock lock = new ReentrantLock();
    Condition a = lock.newCondition();
    Condition b = lock.newCondition();
    Object object = new Object();
    public void ma(){
        lock.lock();
        try {
            System.out.println("a wait");
            a.await();
            System.out.println("run a");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void mb(){
        lock.lock();
        try {
            System.out.println("b wait");
            b.await();
            System.out.println("b run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
