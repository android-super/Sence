package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/27.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RIdListBean extends BaseRequestBean {
    private String id;
    private String page;

    public RIdListBean(String id, String page, String size) {
        this.id = id;
        this.page = page;
        this.size = size;
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

    private String size;
}
