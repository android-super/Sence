package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RShopCommendBean extends BaseRequestBean {
    private String id;
    private String page;
    private String size;

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

    public RShopCommendBean(String id, String page, String size) {
        this.id = id;
        this.page = page;
        this.size = size;
    }
}
