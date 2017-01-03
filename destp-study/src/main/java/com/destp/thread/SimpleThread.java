package com.destp.thread;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class SimpleThread extends Thread {

    private Runnable run;

    public SimpleThread(String name, Runnable run) {
        super(name);
        this.run = run;
    }

    @Override
    public void run() {
        run.run();
    }
}
