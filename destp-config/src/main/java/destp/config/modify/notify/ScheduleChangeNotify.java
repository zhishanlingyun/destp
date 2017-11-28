package destp.config.modify.notify;

import destp.config.modify.ChangeNotify;
import destp.config.modify.ConfigCenter;
import destp.config.modify.ModifyConfig;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ScheduleChangeNotify extends ChangeNotify {

    private ScheduledExecutorService exe =  Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r,"T-config-notify");
            return t;
        }
    });

    @Override
    public void start(final Map visitMap, final ConfigCenter configCenter) {
        exe.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Iterator<Integer> it = visitMap.keySet().iterator();
                Integer id = null;
                ModifyConfig config = null;
                while (it.hasNext()){
                    id = it.next();
                    config = (ModifyConfig) visitMap.get(id);
                    String newmd5 = configCenter.getConfigById(id,config.getClass()).getMD5();
                    String oldmd5 = config.getMD5();
                    if(!oldmd5.equals(newmd5)){
                        changeNotify(configCenter);
                    }
                }
            }
        },1,10, TimeUnit.SECONDS);
    }

}
