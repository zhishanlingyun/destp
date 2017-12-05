package destp.app.crawler.process.processer;

import com.destp.base.util.CommonUtils;
import destp.app.crawler.config.CronConfig;
import destp.app.crawler.process.Processer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JdProcesser extends Processer {

    private static final Logger log = LoggerFactory.getLogger(JdProcesser.class);
    public void process(String ctx) {
        String path = CronConfig.getConfig().getString("target.dir");
        String abspath = CronConfig.getConfig().getString("address.abspath");
        String matcherpath = CronConfig.getConfig().getString("matcher.path");
        System.out.println("cxt is "+ctx);
        System.out.println("matcher is "+matcherpath);
        System.out.println("---"+StringUtils.substringAfter(ctx,matcherpath));
        InputStream in = getConn().Get(abspath+ctx);
        File file = new File(path+StringUtils.substringAfter(ctx,matcherpath));
        try {
            String html = CommonUtils.toString(in);
            Document content = Jsoup.parse(html);
            Element cc = content.select("#fileContentContainer").first();
            String code = "";
            if(null!=cc){
                 cc.textNodes().get(0).getWholeText();
            }
            System.out.println("file is "+file.getAbsolutePath());
            FileUtils.touch(file);
            FileUtils.write(file,code,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("成功保存{}",file.getName());
    }
}
