package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RMyInfoBean extends BaseRequestBean {
    private String uid;
    private String type;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RMyInfoBean(String uid, String type) {
        this.uid = uid;
        this.type = type;
    }
}
