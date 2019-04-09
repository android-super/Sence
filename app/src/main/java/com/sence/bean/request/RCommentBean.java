package com.sence.bean.request;

import com.sence.bean.base.BaseImageRequestBean;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RCommentBean extends BaseImageRequestBean {
    public RCommentBean(String uid, String star, String content) {
        this.uid = uid;
        this.star = star;
        this.content = content;
    }

    private String uid;

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

    private String star;
    private String content;
}
