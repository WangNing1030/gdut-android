package com.wangning.stuinfo;

import org.litepal.crud.LitePalSupport;

/**
 * 学生信息：姓名 + 成绩 + 头像
 * <p>
 * LitePal 3.0.0 版本及以上不支持存储二进制文件，
 * 将 图片 转为 base64 字符串存储
 */
public class Student extends LitePalSupport {

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生成绩
     */
    private Float score;

    /**
     * 头像：bitmap 转为 base64
     */
    private String avatar;

    public Student() {
    }

    public Student(String name, Float score, String avatar) {
        this.name = name;
        this.score = score;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
