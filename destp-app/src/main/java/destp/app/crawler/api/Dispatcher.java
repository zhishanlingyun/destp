package destp.app.crawler.api;

import com.destp.base.core.AbstractAsyLifePlug;
import destp.app.crawler.process.Processer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Dispatcher extends AbstractAsyLifePlug{

    private BlockingQueue<String> cxtQueue = new ArrayBlockingQueue<String>(50);

    private List<Processer> processers = new ArrayList<Processer>();

    protected void doStart() {
        if(CollectionUtils.isNotEmpty(processers)){
            for(Processer processer : processers){
                processer.setCtxQueue(cxtQueue);
                processer.start();
            }
        }
    }

    public void sendCtx(String cxt){
        if(StringUtils.isEmpty(cxt)) return;
        try {
            cxtQueue.put(cxt);
        } catch (InterruptedException e) {

        }
    }

    public Dispatcher addProcesser(Processer processer){
        processers.add(processer);
        return this;
    }

}
