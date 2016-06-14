package com.destp.service;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
@Service("printService")
public class PrintService {

    public String out(String msg){
        return msg;
    }

}
