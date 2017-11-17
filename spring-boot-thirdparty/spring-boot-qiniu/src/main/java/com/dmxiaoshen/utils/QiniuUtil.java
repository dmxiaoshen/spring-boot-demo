package com.dmxiaoshen.utils;

import com.dmxiaoshen.constants.QiniuConstants;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;

/**
 * Created by hzhsg on 2017/11/17.
 */
public class QiniuUtil {
    private static Auth auth;
    private static String url;
    private static String bucket;

    static {
        auth = Auth.create(QiniuConstants.ACCESS_KEY, QiniuConstants.SECRET_KEY);
        if(QiniuConstants.PREFIX_URL.lastIndexOf("/")==QiniuConstants.PREFIX_URL.length()-1){
            url = QiniuConstants.PREFIX_URL;
        }else{
            url = QiniuConstants.PREFIX_URL+"/";
        }
        bucket = QiniuConstants.BUCKET;
    }

    private static String keyToUrl(String key){
        return new StringBuilder(url).append(key).toString();
    }

    public static String uploadFile(File file, String key) throws QiniuException{
        UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone0()));
        uploadManager.put(file,key,auth.uploadToken(bucket));
        return keyToUrl(key);
    }

    public static String uploadFile(byte[] file,String key) throws QiniuException{
        UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone0()));
        uploadManager.put(file,key,auth.uploadToken(bucket));
        return keyToUrl(key);
    }

}
