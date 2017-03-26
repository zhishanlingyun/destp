package com.destp.spring.bean.scan.service.impl;

import com.destp.spring.bean.scan.dto.Lock;
import com.destp.spring.bean.scan.service.SearchService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
@Service("pickerservice")
public class PickerService implements SearchService {
    @Override
    public String foo(String msg) {
        String str = this.getClass().getName()+"-foo";
        System.out.println("invoke "+str);
        return str;
    }

    @Override
    public String search(Lock lock, String key) {
        return null;
    }
}
