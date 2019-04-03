package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/30.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RContentDetailBean extends BaseRequestBean {
    private String uid;
    private String nid;

    public RContentDetailBean(String uid, String nid) {
        this.uid = uid;
        this.nid = nid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }
}
