package com.sence.bean.request.chat;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/17.
 * package_name is com.sence.bean.request.chat
 * 描述:SenceGit
 */
public class RChatListBean extends BaseRequestBean {
    public RChatListBean(String uid, String to_uid, String page) {
        this.uid = uid;
        this.to_uid = to_uid;
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

    private String uid;
    private String to_uid;
    private String page;
    private String pagesize;

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }
}
