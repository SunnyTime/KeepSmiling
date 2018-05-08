package com.du.keepsmiling.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.util.Map;


/**
 *
 * 提供json转换
 */
public class GsonUtil {

    private static final String TAG = GsonUtil.class.getSimpleName();

    private static final Gson gson = new Gson();

    /**
     * 将对象转为字符串
     * @param obj 要转换的对象
     * @return 返回转换后的字符串
     */
    public static String toJsonString(Object obj){
        if(obj == null){
            return "";
        }
        return gson.toJson(obj);
    }

    /**
     * 将json字符串解析为对象
     * @param json 原json字符串
     * @param classOfT 解析对象得类
     * @param <T>
     * @return 返回解析后的对象
     */
    public static <T> T fromJson(String json, Class<T> classOfT){
        if(json == null || json.length() <1){
            return null;
        }

        return gson.fromJson(json,classOfT);
    }

    /**
     * 根据key获取json字符串对应的值
     * @param json  原json字符串
     * @param key 键值
     * @return 返回key对应的值
     */
    public static String fromJsonString(String json, String key){
        try {
            JsonObject jo = gson.fromJson(json,JsonObject.class);
            JsonElement ele = jo.get(key);
            if(ele != null && !ele.toString().equals("{}")){
                return  ele.toString();
            }
        } catch (JsonSyntaxException e) {
        }
        return "";
    }

    public static String fromJSJsonString(String json, String key){
        try {
            JsonObject jo = gson.fromJson(json,JsonObject.class);
            JsonElement ele = jo.get(key);
            if(ele != null && !ele.toString().equals("{}")){
                return  ele.getAsString();
            }
        } catch (JsonSyntaxException e) {
        }
        return "";
    }

    /**
     *
     * @param json
     * @return
     */
    public static String fromJsonString(String json){
        JsonObject jo = gson.fromJson(json,JsonObject.class);
        return jo.toString();
    }

    /**
     * 将Map转化为Json
     *
     * @param map
     * @return String
     */
    public static <T> T mapToClass(Map<String, Object> map, Class<T> t) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        T o = GsonUtil.fromJson(jsonStr, t);
        return o;
    }


}
