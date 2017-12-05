package destp.app.crawler.process;

import com.destp.base.core.AbstractAsyLifePlug;
import com.destp.base.net.client.Cookie;
import com.destp.base.net.client.IhttpClient;
import com.destp.base.net.client.NConfig;
import com.destp.base.net.client.factory.CookieFactory;
import com.destp.base.net.client.provider.DefaultHttpClient;
import destp.app.crawler.config.CronConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public abstract class Processer extends AbstractAsyLifePlug {

    private BlockingQueue<String> ctxQueue;

    private IhttpClient client;

    public Processer(ArrayBlockingQueue<String> ctxQueue) {
        this.ctxQueue = ctxQueue;
        build();
    }

    public Processer() {
        build();
    }

    private void build(){
        NConfig config = new NConfig();
        Cookie cookie = new Cookie();
        cookie.setName( CronConfig.getConfig().getString("cookie.login.key"));
        cookie.setValue(CronConfig.getConfig().getString("cookie.login.value"));
        cookie.setDomain(CronConfig.getConfig().getString("cookie.login.domain"));
        cookie = CookieFactory.create("def",cookie);
        config.setLogCookie(cookie);
        client = new DefaultHttpClient().config(config);
    }

    public IhttpClient getConn(){
        return client;
    }

    public void setCtxQueue(BlockingQueue<String> queue){
        this.ctxQueue = queue;
    }

    protected void doStart() {
        Runnable run = new Runnable() {
            public void run() {
                String ctx = null;
                while (isRunning()){
                    ctx = ctxQueue.poll();
                    if(StringUtils.isEmpty(ctx)){
                        continue;
                    }
                    process(ctx);
                }
            }
        };
        init(run);
        exe.setDaemon(true);
        exe.start();
    }

    public abstract void process(String ctx);

}
