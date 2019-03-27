package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RShopDetailsBean extends BaseRequestBean {
    private String id;
    private String uid;

    public RShopDetailsBean(String id, String uid) {
        this.id = id;
        this.uid = uid;
    }

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
}
