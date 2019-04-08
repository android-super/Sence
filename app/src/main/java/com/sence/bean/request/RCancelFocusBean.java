package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RCancelFocusBean extends BaseRequestBean {
    private String uid;
    private String u_to;

    public RCancelFocusBean(String uid, String u_to) {
        this.uid = uid;
        this.u_to = u_to;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getU_to() {
        return u_to;
    }

    public void setU_to(String u_to) {
        this.u_to = u_to;
    }
}
