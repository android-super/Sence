package com.sence.bean.request.chat;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/10.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RUtoBean extends BaseRequestBean {
    public RUtoBean(String uid, String u_to) {
        this.uid = uid;
        this.u_to = u_to;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String uid;
    private String u_to;

    public String getU_to() {
        return u_to;
    }

    public void setU_to(String u_to) {
        this.u_to = u_to;
    }
}
