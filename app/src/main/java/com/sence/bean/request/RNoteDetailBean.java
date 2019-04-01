package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/29.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RNoteDetailBean extends BaseRequestBean {
    private String uid;
    private String nid;
    private String page;

    public RNoteDetailBean(String uid, String nid, String page) {
        this.uid = uid;
        this.nid = nid;
        this.page = page;
        this.pagesize = "10";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
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

    private String pagesize;
}
