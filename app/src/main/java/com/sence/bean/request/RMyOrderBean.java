package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class MyOrderBean extends BaseRequestBean {
    private String uid;
    private String page;
    private String size;
    private String status;


    public MyOrderBean(String uid, String page, String size, String status) {
        this.uid = uid;
        this.page = page;
        this.size = size;
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
