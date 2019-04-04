package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RSearchBean extends BaseRequestBean {
    private String search;
    private String uid;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public RSearchBean(String search, String uid) {
        this.search = search;
        this.uid = uid;
    }
}
