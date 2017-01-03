package com.destp.thread.communicate;

import com.destp.common.CommonUtil;
import jodd.util.StringUtil;

/**
 * 生产消费模式
 */
public class PCTest {

    //值对象
    class ValueObject{
        private String value;

        public ValueObject(String value) {
            this.value = value;
        }

        public ValueObject() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    class ProducerValue extends Thread{
        private Object lock;
        ValueObject valueObject;

        public ProducerValue(String name, Object lock, ValueObject valueObject) {
            super(name);
            this.lock = lock;
            this.valueObject = valueObject;
        }

        private void setValue(){
            synchronized (lock){
                if(!StringUtil.isEmpty(valueObject.getValue())){
                    try {
                        System.out.println(this.getName()+" 暂停");
                        lock.wait();
                        System.out.println(this.getName()+" 启动");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String str = System.currentTimeMillis()+"";
                System.out.println(this.getName()+" setValue "+str);
                valueObject.setValue(str);
            }
        }

        @Override
        public void run() {
            while (true){
                setValue();
                CommonUtil.sleep(2);
            }
        }
    }

    class ConsumerValue extends Thread{
        private Object lock;
        ValueObject valueObject;

        public ConsumerValue(String name, Object lock, ValueObject valueObject) {
            super(name);
            this.lock = lock;
            this.valueObject = valueObject;
        }

        private void getValue(){
            synchronized (lock){
                if(StringUtil.isEmpty(valueObject.getValue())){
                    System.out.println(this.getName()+" 发送通知");
                    lock.notify();
                }
                System.out.println(this.getName()+" value : "+valueObject.getValue());
                valueObject.setValue(null);
            }
        }

        @Override
        public void run() {
            while (true){
                getValue();
                CommonUtil.sleep(2);
            }
        }
    }

    public void one2one(){
        Object lock = new Object();
        ValueObject valueObject = new ValueObject();
        ProducerValue p = new ProducerValue("producer",lock,valueObject);
        ConsumerValue c = new ConsumerValue("consumer",lock,valueObject);
        p.start();
        c.start();
    }

    public static void main(String[] args){
        PCTest pt = new PCTest();
        pt.one2one();
    }

}
