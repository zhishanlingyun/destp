package destp.config;

import java.math.BigDecimal;
import java.util.List;

public interface Config extends BaseConfig{

    public String getString(String key);

    public String getString(String key,String def);

    public boolean getBoolean(String key);

    public boolean getBoolean(String key,boolean def);

    public byte getByte(String key);

    public byte getByte(String key,byte def);

    public int getInt(String key);

    public int getInt(String key,int def);

    public double getDouble(String key);

    public double getDouble(String key,double def);

    public float getFloat(String key);

    public float getFloat(String key,float def);

    public BigDecimal getBigDecimal(String key);

    public BigDecimal getBigDecimal(String key, BigDecimal def);

    public List getList(String key);

}
