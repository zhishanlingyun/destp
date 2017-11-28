package destp.config;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ConfigReader {

    private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);

    private Map<Class,BaseConfig> configMap = new LinkedHashMap<Class, BaseConfig>(10);

    private Set<ConfigBuilder> builders = new LinkedHashSet();

    private List<String> pathLocals;

    public synchronized void init(){
        if(CollectionUtils.isEmpty(pathLocals)){
            throw new NullPointerException("配置文件路径为空");
        }
        Map<String,ConfigBuilder> supportBuilderMap = new LinkedHashMap<String, ConfigBuilder>();
        for(ConfigBuilder builder : builders){
            supportBuilderMap.put(builder.support(),builder);
        }
        Multimap<String,String> groupMap = ArrayListMultimap.create();
        String type = null;
        for(String fn:pathLocals){
            type = fn.substring(fn.lastIndexOf(".")+1);
            if(supportBuilderMap.containsKey(type)){
                groupMap.put(type,fn);
            }else {
                log.warn("{} 是不支持的类型",type);
            }
        }
        Iterator<String> typeIt = groupMap.keySet().iterator();
        String support = null;
        Collection<String> paths = null;
        while (typeIt.hasNext()){
            support = typeIt.next();
            paths = groupMap.get(support);
            ConfigBuilder builder = supportBuilderMap.get(support);
            BaseConfig baseConfig = builder.builder(paths);
            configMap.put(builder.getConfig(),baseConfig);
        }
        log.info("配置文件加载完毕");
        log.debug("配置文件加载文件{}",Arrays.toString(pathLocals.toArray()));
        log.debug("支持的配置文件构建器{}",builders);
    }

    public BaseConfig getConfig(Class<? extends BaseConfig> clazz){
        return configMap.get(clazz);
    }

    public synchronized void setPathLocals(List<String> pathLocals) {
        this.pathLocals = pathLocals;
    }

    public synchronized ConfigReader registerBuilder(ConfigBuilder builder){
        builders.add(builder);
        return this;
    }

}
