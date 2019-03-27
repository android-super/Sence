package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class ROrderDetailsBean extends BaseRequestBean {
    private String oid;
    private String uid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ROrderDetailsBean(String oid, String uid) {
        this.oid = oid;
        this.uid = uid;
    }
}
