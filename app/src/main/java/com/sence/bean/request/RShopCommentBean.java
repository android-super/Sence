package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RShopCommentBean extends BaseRequestBean {

    private String id;
    private String page;
    private String size;
    private String uid;

    public RShopCommentBean(String id, String page, String size, String uid) {
        this.id = id;
        this.page = page;
        this.size = size;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


}
