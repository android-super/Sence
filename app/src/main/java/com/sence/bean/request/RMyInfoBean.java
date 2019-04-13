package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RMyInfoBean extends BaseRequestBean {
    private String uid;
    private String type;
    private String to_uid;
    private String page;
    private String pagesize;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public RMyInfoBean(String uid, String type, String to_uid, String page, String pagesize) {
        this.uid = uid;
        this.type = type;
        this.to_uid = to_uid;
        this.page = page;
        this.pagesize = pagesize;
    }
}
