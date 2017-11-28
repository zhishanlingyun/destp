package destp.config;

import java.util.Collection;

public interface ConfigBuilder<T extends BaseConfig> {

    public T builder(Collection<String> paths);

    public String support();

    public Class<T> getConfig();

}
