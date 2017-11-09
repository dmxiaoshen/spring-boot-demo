package com.dmxiaoshen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by hzhsg on 2017/11/9.
 */
@Component
@ConfigurationProperties(prefix = "staticProperties")
public class StaticProperties {

    public static String APP_ID;

    public static String APP_NAME;

    public static void setAppId(String appId) {
        APP_ID = appId;
    }

    public static void setAppName(String appName) {
        APP_NAME = appName;
    }
}
