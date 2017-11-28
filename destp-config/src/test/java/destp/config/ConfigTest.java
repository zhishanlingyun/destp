package destp.config;

import jodd.util.ThreadUtil;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConfigTest {

    private PropertiesConfiguration config;

    private XMLConfiguration xmlConfig;

    private String properties = "cf.properties";

    private String xml = "gui.xml";

    //private ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder;
    private FileBasedConfigurationBuilder<PropertiesConfiguration> builder;
    @Before
    public void init(){
        //Configurations configs = new Configurations();
        URL url = ConfigTest.class.getClassLoader().getResource(properties);
        System.out.println(url);
        /*try {
            config = configs.properties(url);
            config.addProperty("fff","ggggggg");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }*/
        Parameters params = new Parameters();
        builder =
                new FileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class)
                        .configure(params.fileBased().setEncoding("UTF-8")
                                .setURL(url));
        //builder.setAutoSave(true);
        //PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(),null, 1, TimeUnit.MILLISECONDS);
        //trigger.start();
        try {
            config =  builder.getConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void foo(){
        System.out.println("username is "+new String(config.getString("username").getBytes()));
        System.out.println("password is "+config.getString("passwordd","王五"));
        System.out.println("baidu name is "+config.getString("baidu.username"));
    }

    @Test
    public void dym(){
        try {
            System.out.println("baidu url is "+builder.getConfiguration().getString("baidu.url"));
            ThreadUtil.sleep(1000*10);
            System.out.println("baidu url is "+builder.getConfiguration().getString("baidu.url"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void xml(){
        String backColor = xmlConfig.getString("colors.background");
        String textColor = xmlConfig.getString("colors.text");
        String linkNormal = xmlConfig.getString("colors.link[@normal]");
        String defColor = xmlConfig.getString("colors.default");
        int rowsPerPage = xmlConfig.getInt("rowsPerPage");
        List<Object> buttons = xmlConfig.getList("buttons.name");
        System.out.println("backColor = "+backColor);
        System.out.println("textColor = "+textColor);
        System.out.println("linkNormal = "+linkNormal);
        System.out.println("defColor = "+defColor);
        System.out.println("rowsPerPage = "+rowsPerPage);
        for(Object name : buttons){
            System.out.println("name is "+(String) name);
        }
    }

}
