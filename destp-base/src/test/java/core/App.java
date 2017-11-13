package core;

import com.destp.base.core.AbstractAbsLoopLifePlug;
import jodd.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zsly on 17-11-11.
 */
public class App extends AbstractAbsLoopLifePlug{

    private static final Logger log = LoggerFactory.getLogger(App.class);

    private AtomicInteger count = new AtomicInteger(0);

    public void loop() {
        log.info("----------{}",count.incrementAndGet());
        ThreadUtil.sleep(1000);
    }

    public void doStop() {
        log.info("last count is {}",count.get());
    }

}
