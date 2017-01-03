package service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2016/12/27 0027.
 */
public class ThreadTest {

    private static List<String> list = new CopyOnWriteArrayList<String>();

    public static void main(String[] args){

        Thread m2 = new Thread(new ReadRun(),"读取线程");
        m2.start();
        System.out.println("m2{} "+m2.isAlive()+"\t"+m2.isDaemon()/*+"\t"+m2.interrupted()*/+"\t"+m2.isInterrupted());
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2{} "+m2.isAlive()+"\t"+m2.isDaemon()/*+"\t"+m2.interrupted()*/+"\t"+m2.isInterrupted());
        System.out.println("中断m2线程");
        m2.interrupt();

        System.out.println("m2{} "+m2.isAlive()+"\t"+m2.isDaemon()/*+"\t"+m2.interrupted()*/+"\t"+m2.isInterrupted());
        System.out.println("m2{} "+m2.isAlive()+"\t"+m2.isDaemon()/*+"\t"+m2.interrupted()*/+"\t"+m2.isInterrupted());
    }

    static class ReadRun implements Runnable{
        public void run() {
            while (true){
                if(list.isEmpty()){
                    System.out.println("list is empty {} ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                    continue;
                }
                for(String s : list){
                    System.out.print(s+"\t");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println();
            }
        }
    }

    static class ModifRun implements Runnable{
        public void run() {
            int n = 0;
            while (true){
                list.add("a"+n++);
                if(n%10==0){
                    list.clear();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
