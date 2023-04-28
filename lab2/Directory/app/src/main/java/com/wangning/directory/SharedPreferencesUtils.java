package com.wangning.directory;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SharedPreferencesUtils {
    /**
     * 保存 name 和 phone 到 data.xml
     *
     * @param context     context
     * @param contactInfo contactInfo
     * @return success
     */
    public static boolean saveContactInfo(Context context, ContactInfo contactInfo) {
        SharedPreferences sp = context.getSharedPreferences("data",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("name", contactInfo.getName());
        edit.putString("phone", contactInfo.getPhone());
        edit.commit();
        return true;
    }

    /**
     * 获取 contactInfo : 从 data.xml 中获取数据
     *
     * @param context context
     * @return map<name, phone>
     */
    public static List<ContactInfo> getContactInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("data",
                Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        String phone = sp.getString("phone", "");
        ContactInfo contactInfo = new ContactInfo(name, phone);
        List<ContactInfo> list = new ArrayList<>();
        list.add(contactInfo);
        return list;
    }

    /**
     * 修改数据 : 根据 name 修改 phone = newContactInfo.phone
     *
     * @param context        context
     * @param newContactInfo newContactInfo
     */
    public static void updateContactInfo(Context context, ContactInfo newContactInfo) {
        List<ContactInfo> contactInfo = getContactInfo(context);
        for (ContactInfo info : contactInfo) {
            if (Objects.equals(info.getName(), newContactInfo.getName())) {
                deleteAll(context);
                saveContactInfo(context, newContactInfo);
            }
        }
    }

    /**
     * 删除所有数据
     *
     * @param context context
     */
    public static void deleteAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences("data",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        // 删除所有数据
        edit.clear();
        edit.commit();
    }
}
