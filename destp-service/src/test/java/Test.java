import com.destp.base.IocContent;
import com.destp.service.PrintService;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class Test {
    public static void main(String[] args){
        /*PrintService printService = (PrintService) IocContent.getBean("printService");
        System.out.println(printService.out("sdfsdfsdfsdf"));*/

        String a = "a";
        String b = "b";
        String c = "a"+"b";
        String d = "ab";
        String e = new String("a"+"b");
        String f = new String("ab");
        System.out.println((a+b)=="a"+"b");
        System.out.println(c=="ab");
        System.out.println(c==d);
        System.out.println(c==e);
        System.out.println(d==f);

        BasicClientCookie cookie = new BasicClientCookie("","");


    }
}
