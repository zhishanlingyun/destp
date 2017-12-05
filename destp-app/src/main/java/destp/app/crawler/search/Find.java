package destp.app.crawler.search;

import com.destp.base.core.AbstractLifePlug;
import com.destp.base.net.client.Cookie;
import com.destp.base.net.client.IhttpClient;
import com.destp.base.net.client.NConfig;
import com.destp.base.net.client.factory.CookieFactory;
import com.destp.base.net.client.provider.DefaultHttpClient;
import destp.app.crawler.api.Dispatcher;
import destp.app.crawler.api.Rule;
import destp.app.crawler.config.CronConfig;
import destp.app.crawler.domain.Element;
import destp.app.crawler.domain.Url;

import java.io.InputStream;
import java.util.List;

/**
 * Created by zsly on 17-11-11.
 * 寻找策略
 */
public abstract class Find extends AbstractLifePlug{

    private IhttpClient client;

    private Dispatcher dispatcher;

    public void init(){
        NConfig config = new NConfig();
        Cookie cookie = new Cookie();
        cookie.setName( CronConfig.getConfig().getString("cookie.login.key"));
        cookie.setValue(CronConfig.getConfig().getString("cookie.login.value"));
        cookie.setDomain(CronConfig.getConfig().getString("cookie.login.domain"));
        cookie = CookieFactory.create("def",cookie);
        config.setLogCookie(cookie);
        client = new DefaultHttpClient().config(config);
    }

    public Find() {
    }

    public Find(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    protected void doStart() {
        init();
        dispatcher.start();
    }

    @Override
    protected void doStop() {

    }

    public IhttpClient getConn(){
        return client;
    }

    public void send(String ctx){
        dispatcher.sendCtx(ctx);
    }

    public  List<Url> next(Url url){
        InputStream in = getConn().Get(url.getUrl());
        String ctx = getContent(in);
        return getNexts(ctx);
    }

    public abstract String getContent(InputStream in);

    public abstract List<Url> getNexts(String ctx);

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
}
