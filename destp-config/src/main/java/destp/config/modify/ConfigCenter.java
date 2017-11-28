package destp.config.modify;

/**
 * 数据中心,被观察的配置文件源
 */
public interface ConfigCenter {

    public ModifyConfig getConfigById(Integer id,Class<? extends ModifyConfig> clazz);

    public String serializable(ModifyConfig config);

    public ModifyConfig deserializable(String str);

}
