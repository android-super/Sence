package com.sence.bean.request;

import java.io.File;

public class RServiceCommentBean {
    private String uid;
    private String star;
    private String content;
    private File[] head;

    public RServiceCommentBean(String uid, String star, String content, File... head) {
        this.uid = uid;
        this.star = star;
        this.content = content;
        this.head = head;
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


    public File[] getHead() {
        return head;
    }

    public void setHead(File[] head) {
        this.head = head;
    }
}
