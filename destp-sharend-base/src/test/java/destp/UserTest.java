package destp;


import destp.base.biz.hr.dao.UserDao;
import destp.base.biz.hr.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/10/23 0023.
 */
public class UserTest {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-hr.xml");
        UserDao dao = (UserDao)context.getBean("userdao");
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123");
        user.setPhone("19280392990");
        user.setEmail("xxx@aaa.com");
        dao.create("User.createUser",user);
    }

}
