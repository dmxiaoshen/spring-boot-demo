package com.dmxiaoshen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzhsg on 2017/11/9.
 */
public class BaseControllerTest {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    /**
     * url参数用map的k,v填充
     *
     * @param url
     * @param param
     * @return
     */
    protected String fillUrl(String url, Map<String, Object> param) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        param.forEach((k, v) -> {
            uriComponentsBuilder.queryParam(k, v);
        });
        return uriComponentsBuilder.build().toString();
    }

    /**
     * 解决RestTemplate使用get传参数使用不支持对象
     *
     * @param url
     * @param obj
     * @return
     */
    protected String fitGetMappingUrlByObjectParam(String url, Object obj) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        Class clazz = obj.getClass();
        Map<String, Object> map = new HashMap<>();
        getClassFieldNameAndValue(map, obj, clazz);
        map.forEach((k, v) -> {
            uriComponentsBuilder.queryParam(k, v);
        });
        return uriComponentsBuilder.build().toString();
    }


    private void getClassFieldNameAndValue(Map<String, Object> map, Object obj, Class clazz) {
        Field[] fieldList = clazz.getDeclaredFields();
        for (int i = 0; i < fieldList.length; i++) {
            Field field = fieldList[i];
            String name = field.getName();
            String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
            Method method = null;
            try {
                method = obj.getClass().getMethod("get" + methodName);
            } catch (NoSuchMethodException e) {
                continue;
            }
            Object value = null;
            try {
                value = method.invoke(obj);
            } catch (IllegalAccessException e) {
                value = null;
            } catch (InvocationTargetException e) {
                value = null;
            }
            if (value != null) {
                map.put(name, value);
            }

            if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
                getClassFieldNameAndValue(map, obj, clazz.getSuperclass());
            }

        }
    }

    /**
     * 解决 resttemplate的PUT和DELETE方法无法返回值问题
     *
     * @param url
     * @param method
     * @param requestEntity
     * @param responseType
     * @param urlVariables
     * @param <T>
     * @return
     */
    protected <T> ResponseEntity<T> putOrDelete(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... urlVariables) {
        if (method == HttpMethod.PUT || method == HttpMethod.DELETE) {
            ResponseEntity<T> result = testRestTemplate.exchange(url, method, requestEntity, responseType, urlVariables);
            return result;
        }
        return null;
    }

    /**
     * 主要用于PUT，DELETE方法body传参和application-json
     *
     * @param obj
     * @param type
     * @return
     */
    protected HttpEntity<?> buildBodyAndContentType(Object obj, MediaType type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(type);
        HttpEntity<?> requestEntity = new HttpEntity<>(obj, headers);
        return requestEntity;
    }
}
