import com.destp.base.IocContent;
import com.destp.service.PrintService;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class Test {
    public static void main(String[] args){
        PrintService printService = (PrintService) IocContent.getBean("printService");
        System.out.println(printService.out("sdfsdfsdfsdf"));
    }
}
