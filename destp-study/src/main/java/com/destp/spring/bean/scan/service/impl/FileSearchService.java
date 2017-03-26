package com.destp.spring.bean.scan.service.impl;

import com.destp.spring.bean.scan.dto.FileDao;
import com.destp.spring.bean.scan.dto.Lock;
import com.destp.spring.bean.scan.service.Search;
import com.destp.spring.bean.scan.service.SearchService;
import com.destp.spring.bean.xml.DelyLock;
import com.destp.spring.bean.xml.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
@Service("fileservice")
public class FileSearchService implements SearchService {

    @Autowired
    //@Qualifier("fd")
    private FileDao fileDao;

    @Autowired
    private Search search;

    @Override
    @Log(key = "#msg",value = "kkkkkkkkkkkkkkkkkkkk")
    public String foo(String msg) {
        String str = this.getClass().getName()+"-foo";
        System.out.println("invoke "+str);
        fileDao.find();
        return str;
    }

    @Override
    @DelyLock(key="#lock.getLockKey()",expire = "#lock.expire()")
    public String search(Lock lock, String key) {
        String str = this.getClass().getName()+"-search";
        System.out.println("invoke "+str);
        search.search();
        return str;
    }

}
