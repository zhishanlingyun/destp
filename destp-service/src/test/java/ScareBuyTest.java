import com.destp.base.net.http.HttpWapper;
import com.destp.base.parse.HtmlParse;
import com.destp.service.PageService;
import com.destp.service.RequestService;
import org.apache.http.client.CookieStore;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class ScareBuyTest {


    public static String runJs(String jspath,String fun,String... args){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String jsFile = jspath;//"";
        FileReader reader = null;
        String result = null;
        try {
            reader = new FileReader(jsFile);
            engine.eval(reader);
            if(engine instanceof Invocable) {
                Invocable invoke = (Invocable)engine;    // 调用merge方法，并传入两个参数
                result = (String)invoke.invokeFunction(fun,args);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (ScriptException e1){
            e1.printStackTrace();
        }catch (NoSuchMethodException e2){
            e2.printStackTrace();
        }
        return result;
    }

    public static String scarePost(Map<String,String> attrMap){
        String a = "/norder/checkAndSubmitOrder.json?fm=order";
        String checkArray = attrMap.get("orderArrayId");
        String j = attrMap.get("orderDateId");
        String l = "";
        String c = attrMap.get("sid");
        String m = "235bab205babc43b138b226babc43b138b226babc";
        String[] cg = checkArray.split("_");
        int n = Integer.parseInt(cg[cg.length - 1]);
        String h = cg[0] + "" + cg[n] + "" + n;
        h = cg[0] + "" + cg[n] + "" + n;
        l = h + "_" + j + "_" + c + "_" + m;
        String url = runJs("E:\\jd\\\\workerspace\\destp\\\\destp-web\\src\\\\main\\webapp\\static\\ace\\js\\scarebuy.js","ShieldEncoder",l,"li43fevlisdfil234li");
        String[] g = new String[]{"&", "m", "R", "d", "m", "="};
        String o = g[0] + g[1] + g[2];
        o += g[4] + g[3] + g[5];
        a += o + url;
        String k = attrMap.get("submitOrderStr");
        a += "&tk1=" + k;
        return a;
    }

    public static void main(String[] args){
        HttpWapper requestService = new HttpWapper();
        HtmlParse pageService = new HtmlParse();
        CookieStore cookieStore = requestService.loadCookies("E:\\data\\cookies1.txt");
        //String url = "http://p.m.jd.com/norder/order.action?wareId=1959137304&wareNum=1&enterOrder=true&sid=58eb27bc413442efc874eed25dc1a977";\
        String url = "http://p.m.jd.com/norder/order.action?enterOrder=true&sid=cc720b281f0e95398a81ec5c30740c50";
        String resp = requestService.doGet(url, cookieStore,null);
        Map<String,String> map = pageService.getPageValById(resp,
                "paymentId",
                "isUsedVirtualPay",
                "securityPayPassword",
                "stockStatus", "sid", "pageSnapshotId", "flowTypeId",
                "isMixPayMentId", "orderKeyUrlId", "orderArrayId", "orderDateId",
                "orderKeyId", "isOpenDefenceUrlId", "submitOrderStr");
        System.out.println(map);
        String orderurl = scarePost(map);
        orderurl="http://p.m.jd.com/"+orderurl;
        requestService.doPost(orderurl, cookieStore, null);
        /*String res = runJs("");
        System.out.println(res);*/

    }

}
