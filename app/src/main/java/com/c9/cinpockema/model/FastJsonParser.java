package com.c9.cinpockema.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marionette on 2016/5/30.
 */
//对服务器传递的Json数据进行解析
public class FastJsonParser {
    //解析json数组
    public static List listParse(String jsonStr, Class cls) {
        List list = new ArrayList();
        try {
            list = JSON.parseArray(jsonStr, cls);
        } catch (Exception e) {
            Log.e("parse error:", e.getMessage());
        }
        return list;
    }

    //解析jsonobject
    public static Object objectParse(String jsonStr, Class cls) {
        Object object = new Object();
        try {
            object = JSON.parseObject(jsonStr, cls);
        } catch (Exception e) {
            Log.e("parse error:", e.getMessage());
        }
        return object;
    }
    //将某个实体转化为json格式的字符串
    public static final String parseToJsonStr(Object object){
        String str = JSON.toJSONString(object);
        return str;
    }
}