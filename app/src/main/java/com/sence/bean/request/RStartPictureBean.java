package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RStartPictureBean extends BaseRequestBean {
    private String imei;
    private String uid;

    public RStartPictureBean(String imei, String uid) {
        this.imei = imei;
        this.uid = uid;
    }
}
