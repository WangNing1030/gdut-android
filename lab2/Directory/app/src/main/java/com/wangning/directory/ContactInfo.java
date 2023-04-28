package com.wangning.directory;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * 联系人信息
 */
public class ContactInfo extends LitePalSupport {

    /**
     * 联系人姓名
     */
    @Column(nullable = false)
    private String name;

    /**
     * 联系人电话
     */
    @Column(nullable = false)
    private String phone;

    public ContactInfo() {
    }

    public ContactInfo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
