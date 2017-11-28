package destp.config.modify;

import java.io.Serializable;

/**
 * 读取可以动态变化的配置文件
 */
public interface ModifyConfig<T extends ModifyConfig> extends Serializable{

    public int getId();

    public void setId(int id);

    public T getConfig();

    public String getMD5();

}
