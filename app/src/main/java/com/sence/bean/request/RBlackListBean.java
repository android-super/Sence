package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RBlackListBean extends BaseRequestBean {
    private String uid;
    private String page;
    private String pagesize;

    public RBlackListBean(String uid, String page, String pagesize) {
        this.uid = uid;
        this.page = page;
        this.pagesize = pagesize;
    }
}
