package com.destp.spring.bean.scan.service;

import com.destp.spring.bean.scan.dto.Lock;
import com.destp.spring.bean.xml.DelyLock;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public interface SearchService {

    public String foo(String msg);


    public String search(Lock lock, String key);

}
