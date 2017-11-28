package destp.config.builder;

import destp.config.BaseConfig;
import destp.config.ConfigBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractConfigBuilder<T extends BaseConfig> implements ConfigBuilder {

    private static String DEF_ENCODING = "UTF-8";

    private String encoding = DEF_ENCODING;

    @Override
    public boolean equals(Object obj) {
        AbstractConfigBuilder other = (AbstractConfigBuilder) obj;
        String my = new StringBuilder(getConfig().getSimpleName()).append("_").append(support()).toString();
        String you = new StringBuilder(other.getConfig().getSimpleName()).append("_").append(support()).toString();
        return my.equals(you);
    }

    @Override
    public int hashCode() {
        return getConfig().hashCode() + support().hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder(getConfig().getSimpleName()).append("_").append(support()).toString();
    }


    @Override
    public T builder(Collection paths) {
        List<URL> urls = new ArrayList<URL>();
        for(Object path : paths){
            URL url = getClass().getClassLoader().getResource((String) path);
            urls.add(url);
        }
        return build(urls);
    }

    public abstract T build(List<URL> urls);

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
