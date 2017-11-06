package com.destp.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zsly on 17-10-29.
 */
public class Test {

    private static final Logger log = LoggerFactory.getLogger(Test.class);

    private static final Logger dump = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void foo(){
        log.info("hello log4j {}","abc");
    }

    @org.junit.Test
    public void trace(){
        dump.trace("xxxxxxxxxxxxxxx");
    }

}
