package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RAddressDeleteBean extends BaseRequestBean {
    private String uid;
    private String id;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RAddressDeleteBean(String uid, String id) {
        this.uid = uid;
        this.id = id;
    }
}
