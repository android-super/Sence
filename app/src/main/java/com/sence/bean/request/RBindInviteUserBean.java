package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RBindInviteUserBean extends BaseRequestBean {
    private String uid;
    private String code;

    public RBindInviteUserBean(String uid, String code) {
        this.uid = uid;
        this.code = code;
    }
}
