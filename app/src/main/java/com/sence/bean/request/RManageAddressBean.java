package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RManageAddressBean extends BaseRequestBean {
    private String uid;
    private String page;
    private String size;


    public RManageAddressBean(String uid, String page, String size) {
        this.uid = uid;
        this.page = page;
        this.size = size;
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

}
