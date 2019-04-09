package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/4.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RNidBean extends BaseRequestBean {
    private String uid;
    private String nid;

    public RNidBean(String uid, String nid) {
        this.uid = uid;
        this.nid = nid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
