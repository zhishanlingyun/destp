package destp.config.builder;

import destp.config.BaseConfig;
import destp.config.ConfigBuilder;
import destp.config.cfg.PropertiesConfig;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.combined.MultiFileConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Collection;
import java.util.List;

public class PropertiesConfigBuilder extends AbstractConfigBuilder<PropertiesConfig> {

    private static final Logger log = LoggerFactory.getLogger(PropertiesConfigBuilder.class);

    private CompositeConfiguration configure;

    private String supportFile = "properties";

    public PropertiesConfigBuilder() {
        init();
    }

    private void init(){
        configure = new CompositeConfiguration();
    }

    @Override
    public PropertiesConfig build(List<URL> urls) {
        try {
            for(URL url : urls){
                Parameters params = new Parameters();
                FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class);
                builder.configure(params.properties().setEncoding(getEncoding()).setURL(url));
                configure.addConfiguration(builder.getConfiguration());
            }
        } catch (ConfigurationException e) {
            log.error("PropertiesConfigBuilder 构建失败",e);
        }
        PropertiesConfig config = new PropertiesConfig(configure);
        return config;
    }

    @Override
    public String support() {
        return supportFile;
    }

    @Override
    public Class<PropertiesConfig> getConfig() {
        return PropertiesConfig.class;
    }
}
