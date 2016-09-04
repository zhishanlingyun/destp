package com.destp.base.parallel;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/7/16 0016.
 */
public class CountDownThreadProcess {

    private static final Logger log = Logger.getLogger(CountDownThreadProcess.class);

    public static void process(int nThread, final RunnableHandler handler){
        final CountDownLatch start = new CountDownLatch(1);
        for(int i=0;i<nThread;i++){
            Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        log.info(this.getName() + "准备执行");
                        Map map = handler.doBefore();
                        start.await();
                        try {
                            log.info(this.getName() + "开始执行");
                            handler.doWorker(map);
                            handler.doAfter();
                            log.info(this.getName() + "执行结束");
                        }catch (Exception e){
                            handler.doExcepter();
                        }
                    } catch (InterruptedException e) {
                        log.error(e);
                    }
                }
            };
            t.start();
        }

        try {
            while (handler.start()){
                try {
                    start.countDown();
                } finally {
                    break;
                }
            }
        } catch (Exception e) {
            log.error("开始条件异常",e);
        } finally {

        }
        log.info("方法执行结束");

    }

}
