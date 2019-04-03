package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/30.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RCommentListBean extends BaseRequestBean {
    public RCommentListBean(String uid, String rid, String type,String page) {
        this.uid = uid;
        this.rid = rid;
        this.type = type;
        this.page = page;
        this.pagesize = "10";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    private String uid;
    private String rid;
    private String type;
    private String page;
    private String pagesize;
}
