package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class REditroAddressBean extends BaseRequestBean {
    private String uid;
    private String id;
    private String address;
    private String area;
    private String phone;
    private String username;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public REditroAddressBean(String uid, String id, String address, String area, String phone, String username) {
        this.uid = uid;
        this.id = id;
        this.address = address;
        this.area = area;
        this.phone = phone;
        this.username = username;
    }
}
