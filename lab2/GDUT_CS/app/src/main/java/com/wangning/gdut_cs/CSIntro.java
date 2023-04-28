package com.wangning.gdut_cs;

import java.io.Serializable;

public class CSIntro implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String content;

    private int img;

    public CSIntro() {
    }


    public CSIntro(String title, int img, String content) {
        this.title = title;
        this.img = img;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CSIntro{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
