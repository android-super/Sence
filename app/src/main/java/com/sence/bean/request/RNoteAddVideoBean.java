package com.sence.bean.request;

import com.sence.bean.base.BaseImageRequestBean;

/**
 * Created by zwy on 2019/4/13.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RNoteAddVideoBean extends BaseImageRequestBean {
    private String uid;
    private String type;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public RNoteAddVideoBean(String uid, String type, String content) {
        this.uid = uid;
        this.type = type;
        this.content = content;
    }

}
