package destp.app.crawler.config;

import destp.config.Config;
import destp.config.ConfigReader;
import destp.config.builder.PropertiesConfigBuilder;
import destp.config.cfg.PropertiesConfig;

import java.util.Arrays;

public class CronConfig {

    private static ConfigReader reader;

    static {
        reader = new ConfigReader();
        reader.setPathLocals(Arrays.asList("crawler/config.properties"));
        reader.registerBuilder(new PropertiesConfigBuilder()).init();
    }

    public static Config getConfig(){
        return (Config) reader.getConfig(PropertiesConfig.class);
    }

}
