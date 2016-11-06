package service;

import com.destp.sharend.shop.biz.home.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
public class DemoServiceTest {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        DemoService service = (DemoService) context.getBean("demoService");
        service.getUsers();
    }

}
