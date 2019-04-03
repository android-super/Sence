package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RUserDetailBean extends BaseRequestBean {
    private String uid;
    private String page;
    private String pagesize;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public RUserDetailBean(String uid, String page, String pagesize) {
        this.uid = uid;
        this.page = page;
        this.pagesize = pagesize;
    }
}
