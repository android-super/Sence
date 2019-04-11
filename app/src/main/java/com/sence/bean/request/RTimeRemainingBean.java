package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RTimeRemainingBean extends BaseRequestBean {
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public RTimeRemainingBean(String oid) {
        this.oid = oid;
    }
}
