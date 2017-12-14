package com.dmxiaoshen.constants;

import com.dmxiaoshen.util.SpringPropertiesUtil;

/**
 * Created by hzhsg on 2017/12/14.
 */
public class CommonConstants {

    public static final String APP_ID = SpringPropertiesUtil.getPropertiesValue("${staticProperties.APP_ID}");
    public static final String APP_NAME = SpringPropertiesUtil.getPropertiesValue("${staticProperties.APP_NAME}");
}
