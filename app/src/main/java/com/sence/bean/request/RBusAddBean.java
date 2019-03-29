package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/27.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RBusAddBean extends BaseRequestBean {
    private String gid;
    private String uid;
    private String num;

    public RBusAddBean(String gid, String uid) {
        this.gid = gid;
        this.uid = uid;
    }

    public RBusAddBean(String gid, String uid, String num, String type) {
        this.gid = gid;
        this.uid = uid;
        this.num = num;
        this.type = type;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
}
