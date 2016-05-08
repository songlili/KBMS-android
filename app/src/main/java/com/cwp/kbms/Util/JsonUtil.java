package com.cwp.kbms.Util;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 曹伟鹏 on 2016/4/29.
 */
public class JsonUtil {

    Gson gson = new Gson();


    /**
     * 得到一个json类型的字符串
     * @param key 键
     * @param value 值
     * @return json类型的字符串
     * @throws JSONException
     */
    public static String getJsonString(String key,Object value) throws JSONException {
        JSONObject jsonObject= new JSONObject();
        jsonObject.put(key, value);
        return jsonObject.toString();
    }

    /**
     * 得到一个Json对象
     * @param key 键
     * @param value 值
     * @return json对象
     * @throws JSONException
     */

    public static JSONObject getJsonObject(String key,Object value) throws JSONException {
        JSONObject jsonObject= new JSONObject();
        jsonObject.put(key, value);
        return jsonObject;
    }
}
