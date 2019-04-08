package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/29.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RFansBean extends BaseRequestBean {
    private String uid;
    private String page;
    private String pagesize;

    public RFansBean(String uid, String keyword, String page) {
        this.uid = uid;
        this.keyword = keyword;
        this.page = page;
        this.pagesize = "10";
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

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private String keyword;
}
