package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/20.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RAddressAdd extends BaseRequestBean {
    private String uid;
    private String address;
    private String area;
    private String phone;
    private String zipcode;

    public RAddressAdd(String uid, String address, String area, String phone, String zipcode, String username) {
        this.uid = uid;
        this.address = address;
        this.area = area;
        this.phone = phone;
        this.zipcode = zipcode;
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
}
