package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/27.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RUidListBean extends BaseRequestBean {
    public RUidListBean(String uid, String page) {
        this.uid = uid;
        this.page = page;
    }

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

    private String uid;
    private String page;
}
