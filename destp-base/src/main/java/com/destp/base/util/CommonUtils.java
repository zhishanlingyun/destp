package com.destp.base.util;

import com.destp.base.convert.Convert;
import org.apache.commons.collections.MapUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class CommonUtils {

    public static <T,K,V> List<T> convert(Map<K,V> map, Convert<T> _convert){
        if(MapUtils.isNotEmpty(map)){
            ArrayList<T> result = new ArrayList<T>();
            Iterator<K> it = map.keySet().iterator();
            K key = null;
            V value = null;
            while (it.hasNext()){
                key = it.next();
                value = map.get(key);
                T t = _convert.convert(key,value);
                result.add(t);
            }
            return result;
        }
        return null;
    }

    public static String toString(InputStream in) throws IOException {
        final InputStream instream = in;
        if (instream == null) {
            return null;
        }
        try {
            Charset charset = Charset.forName("UTF-8");
            final Reader reader = new InputStreamReader(instream, charset);
            final CharArrayBuffer buffer = new CharArrayBuffer(4096);
            final char[] tmp = new char[1024];
            int l;
            while((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toString();
        } finally {
            instream.close();
        }
    }

    public static boolean isFileUrl(String url){
        if(url.lastIndexOf(".")!=-1&&url.lastIndexOf("..")==-1){
            return true;
        }
        return false;
    }

}
