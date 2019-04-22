package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RRachelBean extends BaseRequestBean {
    private String uid;
    private String to_uid;
    private String type;

    public RRachelBean(String uid, String to_uid, String type) {
        this.uid = uid;
        this.to_uid = to_uid;
        this.type = type;
    }
}
