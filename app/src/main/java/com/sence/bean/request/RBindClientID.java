package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/10.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RBindClientID extends BaseRequestBean {
    public RBindClientID(String uid, String client_id, String device) {
        this.uid = uid;
        this.client_id = client_id;
        this.device = device;
    }

    private String uid;
    private String client_id;
    private String device;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
