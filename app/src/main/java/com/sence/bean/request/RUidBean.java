package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/27.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RUidBean extends BaseRequestBean {
    private String uid;

    public RUidBean(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
