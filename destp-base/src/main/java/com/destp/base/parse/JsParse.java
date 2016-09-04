package com.destp.base.parse;

import jodd.io.FileUtil;
import org.apache.log4j.Logger;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class JsParse {

    private static Logger log = Logger.getLogger(JsParse.class);

    public static String runJs(URL jspath,String fun,String... args){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        //String jsFile = jspath;//"";
        File jsFile = FileUtil.toContainerFile(jspath);;

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
            log.error(e);
        }catch (ScriptException e1){
            log.error(e1);
        }catch (NoSuchMethodException e2){
            log.error(e2);
        }
        return result;
    }

}
