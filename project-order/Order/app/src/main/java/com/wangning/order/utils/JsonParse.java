package com.wangning.order.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangning.order.bean.ShopBean;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {

    private static JsonParse instance;

    // 构造方法私有化
    private JsonParse() {
    }

    /**
     * 单例模式
     *
     * @return JsonParse
     */
    public static JsonParse getInstance() {
        if (instance == null) {
            instance = new JsonParse();
        }
        return instance;
    }

    /**
     * 从 Json 文本中解析出 List<ShopBeen>
     *
     * @param json json
     * @return List<ShopBeen>
     */
    public List<ShopBean> getShopList(String json) {
        // 创建一个TypeToken的匿名子类对象，并调用对象的getType()方法
        Type listType = new TypeToken<List<ShopBean>>() {
        }.getType();
        Gson gson = new Gson();
        // 把获取到的信息集合存到 shopList 中
        List<ShopBean> shopList = gson.fromJson(json, listType);
        return shopList;
    }
}
