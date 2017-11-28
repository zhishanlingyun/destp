package destp.config.modify;

import java.util.Map;

/**
 * 观察配置中心变化，通知其监听者
 */
public abstract class ChangeNotify<T extends ConfigCenter> {

    private ChangeListener listener;

    public abstract void start(Map<Integer,ModifyConfig> visitMap, T configCenter);

    protected void changeNotify(T configCenter){
        if(null!=listener){
            listener.update(configCenter);
        }
    }

}
