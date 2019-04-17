package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class PUserinfoBean extends BaseRequestBean {
    private String uid;
    private String to_uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }

    public PUserinfoBean(String uid, String to_uid) {
        this.uid = uid;
        this.to_uid = to_uid;
    }
}
