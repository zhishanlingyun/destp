package destp.config;

import destp.config.builder.PropertiesConfigBuilder;
import destp.config.builder.XmlConfigBuilder;
import destp.config.cfg.PropertiesConfig;
import destp.config.cfg.XmlConfig;

import java.util.Arrays;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        ConfigReader reader = new ConfigReader();
        reader.setPathLocals(Arrays.asList("cf.properties","cf1.properties"));
        reader.registerBuilder(new PropertiesConfigBuilder()).init();
        Config config = (Config) reader.getConfig(PropertiesConfig.class);
        System.out.println(config.getString("baidu.username","fff"));
        System.out.println(config.getString("greet"));

        ConfigReader reader2 = new ConfigReader();
        reader2.setPathLocals(Arrays.asList("gui.xml"));
        reader2.registerBuilder(new XmlConfigBuilder()).init();
        Config xmlConfig = (Config) reader2.getConfig(XmlConfig.class);
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
