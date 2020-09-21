package com.ssaw.GlobalManagement.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统工具类
 * @type: util
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/10
 */
public class SysUtil {
    /**
     * json对象数组转对象集合
     * @param json 对象数组
     * @param clazz 类.class
     * @param <T>
     * @return 对象集合
     */
    public static <T> List<T> jsonToArrayList(String json, Class<T> clazz){
        //得到JSON对象类型
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        //转换成json数组
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        //实例化一个ArrayList数组
        ArrayList<T> arrayList = new ArrayList<T>();
        //循环数组对象
        for (JsonObject jsonObject : jsonObjects)
        {
            //往数组中存储转换成相应的实体类对象
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        //返回存储了对象的数组
        return arrayList;
    }
}
