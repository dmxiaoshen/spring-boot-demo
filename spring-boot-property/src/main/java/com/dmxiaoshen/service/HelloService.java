package com.dmxiaoshen.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hzhsg on 2017/12/1.
 */
public class HelloService {

    private static  Logger logger = LoggerFactory.getLogger(HelloService.class);

    public static String call(String id,String name,String... opts){
        String opt = null;
        if(opts!=null && opts.length>0){
            opt = opts[0];
        }

        logger.info("-----{},{},{}",id,name,opt);
        return "success";
    }
}
