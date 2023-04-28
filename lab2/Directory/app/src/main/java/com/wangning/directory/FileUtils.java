package com.wangning.directory;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileUtils {

    /**
     * 删除所有数据，删除 data.txt
     *
     * @param context
     */
    public static void deleteAll(Context context) {
        context.deleteFile("data.txt");
    }


    /**
     * 修改 contactInfo : 根据 newContactInfo.name 修改 contactInfo.phone = newContactInfo.phone
     *
     * @param context
     * @param newContact
     * @return
     */
    public static boolean updateContactInfo(Context context, ContactInfo newContact) {
        String content = "";
        FileInputStream fis = null;
        try {
            // 获取文件的输入流对象fis
            fis = context.openFileInput("data.txt");
            // 将输入流对象中的数据转换为字节码的形式
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer); // 通过read()方法读取字节码中的数据
            content = new String(buffer); // 将获取的字节码转换为字符串
            List<ContactInfo> contactList = new ArrayList<>();
            String[] infos = content.split(";"); // 将字符串以“;”分隔后形成一个数组的形式
            for (String info : infos) {
                String[] contact = info.split(":");
                String name = contact[0];
                String phone = contact[1];
                contactList.add(new ContactInfo(name, phone));
            }

            /*
            根据 newContact.name 修改 contactInfo。phone = newContact.phone
             */
            for (ContactInfo contactInfo : contactList) {
                if (Objects.equals(contactInfo.getName(), newContact.getName())) {
                    contactInfo.setPhone(newContact.getPhone());
                }
                deleteAll(context);
                saveContactInfo(context, contactInfo.getName(), contactInfo.getPhone());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存 ContactInfo 到 data.txt
     *
     * @param context context
     * @param name    contactInfo.name
     * @param phone   contactInfo.phone
     * @return success
     */
    public static boolean saveContactInfo(Context context, String name, String
            phone) {
        FileOutputStream fos = null;
        try {
            // 获取文件的输出流对象fos，设置该文件内容可追加
            fos = context.openFileOutput("data.txt",
                    Context.MODE_APPEND);
            // 将数据转换为字节码的形式写入data.txt文件中
            fos.write((name + ":" + phone + ";").getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从 data.txt 中获取存储的 ContactInfo
     *
     * @param context context
     * @return map<name, phone>
     */
    public static List<ContactInfo> getContactInfo(Context context) {
        String content = "";
        FileInputStream fis = null;
        try {
            // 获取文件的输入流对象fis
            fis = context.openFileInput("data.txt");
            // 将输入流对象中的数据转换为字节码的形式
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer); // 通过read()方法读取字节码中的数据
            content = new String(buffer); // 将获取的字节码转换为字符串
            List<ContactInfo> contactList = new ArrayList<>();
            String[] infos = content.split(";"); // 将字符串以“;”分隔后形成一个数组的形式
            for (String info : infos) {
                String[] contact = info.split(":");
                String name = contact[0];
                String phone = contact[1];
                contactList.add(new ContactInfo(name, phone));
            }
            return contactList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
