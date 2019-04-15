package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/12.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RCommentSupportBean extends BaseRequestBean {
    private String uid;
    private String mid;

    public RCommentSupportBean(String uid, String mid) {
        this.uid = uid;
        this.mid = mid;
        this.type = "1";
    }

    private String type;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
