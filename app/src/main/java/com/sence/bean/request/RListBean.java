package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RListBean extends BaseRequestBean {
    public RListBean(String page) {
        this.page = page;
        this.size = "10";
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

    private String page;
    private String size;
}

