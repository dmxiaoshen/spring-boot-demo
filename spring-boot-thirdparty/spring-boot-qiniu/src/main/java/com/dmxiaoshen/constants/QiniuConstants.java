package com.dmxiaoshen.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by hzhsg on 2017/11/17.
 */
@Component
@ConfigurationProperties("qiniu")
public class QiniuConstants {

    public static String BUCKET;
    public static String ACCESS_KEY;
    public static String SECRET_KEY;
    public static String PREFIX_URL;
    public static long TOKEN_EXPIRY;
    public static String POLICY;

    public static void setBucket(String bucket) {
        QiniuConstants.BUCKET = bucket;
    }

    public static void setAccessKey(String accessKey) {
        QiniuConstants.ACCESS_KEY = accessKey;
    }

    public static void setSecretKey(String secretKey) {
        QiniuConstants.SECRET_KEY = secretKey;
    }

    public static void setPrefixUrl(String prefixUrl) {
        QiniuConstants.PREFIX_URL = prefixUrl;
    }

    public static void setTokenExpiry(long tokenExpiry) {
        QiniuConstants.TOKEN_EXPIRY = tokenExpiry;
    }

    public static void setPolicy(String policy) {
        QiniuConstants.POLICY = policy;
    }
}
