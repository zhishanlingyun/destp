package com.destp.thread.pool;

import com.destp.common.CommonUtil;

/**
 * Created by liuli10 on 2017/1/10.
 */
public class PoolTest {

    public static void main(String[] args) {
        MonitorThreadPool pool = new MonitorThreadPool(10);
        pool.execute(new Runnable() {
            public void run() {
                System.out.println("run");
                CommonUtil.sleep(5);
            }
        });
        System.out.println(pool.getCompletedTaskCount());
        CommonUtil.sleep(10);
        pool.shutdownNow();
    }

}
