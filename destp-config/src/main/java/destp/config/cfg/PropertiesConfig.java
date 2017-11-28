package destp.config.cfg;

import destp.config.Config;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;

import java.math.BigDecimal;
import java.util.List;

public class PropertiesConfig implements Config {

    private Configuration cfg;

    public PropertiesConfig(Configuration cfg) {
        this.cfg = cfg;
    }

    @Override
    public String getString(String key) {
        return cfg.getString(key);
    }

    @Override
    public String getString(String key, String def) {
        return cfg.getString(key,def);
    }

    @Override
    public boolean getBoolean(String key) {
        return cfg.getBoolean(key);
    }

    @Override
    public boolean getBoolean(String key, boolean def) {
        return cfg.getBoolean(key,def);
    }

    @Override
    public byte getByte(String key) {
        return cfg.getByte(key);
    }

    @Override
    public byte getByte(String key, byte def) {
        return cfg.getByte(key,def);
    }

    @Override
    public int getInt(String key) {
        return cfg.getInt(key);
    }

    @Override
    public int getInt(String key, int def) {
        return cfg.getInt(key,def);
    }

    @Override
    public double getDouble(String key) {
        return cfg.getDouble(key);
    }

    @Override
    public double getDouble(String key, double def) {
        return cfg.getDouble(key,def);
    }

    @Override
    public float getFloat(String key) {
        return cfg.getFloat(key);
    }

    @Override
    public float getFloat(String key, float def) {
        return cfg.getFloat(key,def);
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return cfg.getBigDecimal(key);
    }

    @Override
    public BigDecimal getBigDecimal(String key, BigDecimal def) {
        return cfg.getBigDecimal(key,def);
    }

    @Override
    public List getList(String key) {
        return cfg.getList(key);
    }
}
