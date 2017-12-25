package com.dmxiaoshen.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hzhsg on 2017/12/11.
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> toList(String json,Class<T> clazz){
      return (List)toCollection(json,List.class,clazz);
    }

    public static <T> LinkedList<T> toLinkedList(String json,Class<T> clazz){
        return (LinkedList)toCollection(json,LinkedList.class,clazz);
    }

    public static <T> Set<T> toSet(String json,Class<T> clazz){
        return (Set)toCollection(json,Set.class,clazz);
    }

    public static <K,V> Map<K,V> toMap(String json,Class<K> keyClazz,Class<V> valueClazz){
        return (Map)toCollection(json,Map.class,keyClazz,valueClazz);
    }

    private static Object toCollection(String json,Class<?> parametrized, Class... parameterClasses){
        try {
            return mapper.readValue(json,mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toJavaBean(String json,Class<T> clazz){
        try {
            return mapper.readValue(json,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonNode toJsonNode(String json){
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapper.valueToTree(json);
    }

//    public static <E> List<E> toList(Object obj, Class<E> clazz) {
//        return (List)toGeneric(obj, List.class, new Class[]{clazz});
//    }
//
//    public static <E> LinkedList<E> toLinkedList(Object obj, Class<E> clazz) {
//        return (LinkedList)toGeneric(obj, LinkedList.class, new Class[]{clazz});
//    }
//
//    public static  <E> Set<E> toSet(Object obj, Class<E> clazz) {
//        return (Set)toGeneric(obj, Set.class, new Class[]{clazz});
//    }
//
//    public static  <K, V> Map<K, V> toMap(Object obj, Class<K> keyClazz, Class<V> valueClazz) {
//        return (Map)toGeneric(obj, Map.class, new Class[]{keyClazz, valueClazz});
//    }
//
//    public static Object toGeneric(Object obj, Class<?> parametrized, Class... parameterClasses) {
//        JavaType type = mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
//        return toGeneric(obj, type);
//    }
//
//    private static Object toGeneric(Object obj, JavaType type) {
//        try {
//            return obj instanceof String?mapper.readValue((String)obj, type):(obj instanceof JsonNode?mapper.readValue(obj.toString(), type):mapper.readValue(mapper.writeValueAsString(obj), type));
//        } catch (IOException var4) {
//            throw new RuntimeException(var4);
//        }
//    }
//
//    public static <E> E toObject(Object obj, Class<E> clazz) {
//        try {
//            return obj instanceof String?(clazz == String.class?(E)obj:mapper.readValue((String)obj, clazz)):(obj instanceof JsonNode?mapper.readValue(obj.toString(), clazz):mapper.readValue(mapper.writeValueAsString(obj), clazz));
//        } catch (IOException var4) {
//            throw new RuntimeException(var4);
//        }
//    }
}
