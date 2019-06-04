package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RShopBean  extends BaseRequestBean {
    private String id;
    private String uid;
    private String version;

    public RShopBean(String id, String uid,String version) {
        this.id = id;
        this.uid = uid;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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


