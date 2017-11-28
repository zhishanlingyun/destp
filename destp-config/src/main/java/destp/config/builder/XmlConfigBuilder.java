package destp.config.builder;

import destp.config.BaseConfig;
import destp.config.cfg.XmlConfig;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;

public class XmlConfigBuilder extends AbstractConfigBuilder<XmlConfig> {

    private static final Logger log = LoggerFactory.getLogger(XmlConfigBuilder.class);

    private final String supportFile = "xml";

    private CompositeConfiguration  configuration;

    public XmlConfigBuilder() {
        configuration = new CompositeConfiguration();
    }

    @Override
    public XmlConfig build(List<URL> urls) {
        try {
            for(URL url : urls){
                Parameters params = new Parameters();
                FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<XMLConfiguration>(XMLConfiguration.class);
                builder.configure(params.xml().setEncoding(getEncoding()).setURL(url));
                configuration.addConfiguration(builder.getConfiguration());
            }
        } catch (ConfigurationException e) {
            log.error("PropertiesConfigBuilder 构建失败",e);
        }
        XmlConfig config = new XmlConfig(configuration);
        return config;
    }

    @Override
    public String support() {
        return supportFile;
    }

    @Override
    public Class getConfig() {
        return XmlConfig.class;
    }
}
