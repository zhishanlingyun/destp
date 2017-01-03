package com.destp.thread.jmm;

import com.destp.common.CommonUtil;
import com.destp.thread.ObjectValue;
import com.destp.thread.ReadWriteService;
import com.destp.thread.SimpleThread;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class VolatileTest {

    private static final Logger log = Logger.getLogger(VolatileTest.class);

    public void volat(){
        final ReadWriteService service = new ViolateReadWrite(new ObjectValue());
        SimpleThread r = new SimpleThread("read-thread", new Runnable() {
            public void run() {
                service.read();
            }
        });
        SimpleThread w = new SimpleThread("write-thread", new Runnable() {
            public void run() {
                service.write();
            }
        });
        w.start();
        r.start();
    }

    public static void main(String[] args) {
        VolatileTest vt = new VolatileTest();
        vt.volat();
    }


    class ViolateReadWrite implements com.destp.thread.ReadWriteService{

        private volatile ObjectValue value;

        public ViolateReadWrite(ObjectValue value) {
            this.value = value;
        }

        public void read() {
            log.info(Thread.currentThread().getName() + "\t"+"value = "+value.getValue());
            CommonUtil.sleep(1);
        }

        public void write() {
            String valuen = String.valueOf(CommonUtil.getRodam(0,10));
            value.setValue(valuen);
            log.info(Thread.currentThread().getName() + "\t"+"set value = "+valuen);
            CommonUtil.sleep(1);
        }
    }
}
