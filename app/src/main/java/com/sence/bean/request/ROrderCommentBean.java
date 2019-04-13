package com.sence.bean.request;

import com.sence.bean.base.BaseImageRequestBean;

public class ROrderCommentBean extends BaseImageRequestBean {
    private String uid;
    private String star;
    private String content;
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public ROrderCommentBean(String uid, String star, String content,  String oid) {
        this.uid = uid;
        this.star = star;
        this.content = content;
        this.oid = oid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
