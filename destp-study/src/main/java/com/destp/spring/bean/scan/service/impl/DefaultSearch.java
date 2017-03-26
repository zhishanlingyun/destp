package com.destp.spring.bean.scan.service.impl;

import com.destp.spring.bean.scan.service.Search;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
@Repository
public class DefaultSearch implements Search {
    @Override
    public void search() {
        System.out.println("DefaultSearch search function");
    }
}
