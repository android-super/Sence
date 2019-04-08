package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/4.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RFocusBean extends BaseRequestBean {
    private String uid;

    public RFocusBean(String uid, String u_to) {
        this.uid = uid;
        this.u_to = u_to;
    }

    private String u_to;

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

