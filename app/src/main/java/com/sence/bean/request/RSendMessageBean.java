package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/10.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RSendMessageBean extends BaseRequestBean {
    private String vid;
    private String uid;
    private String content;

    public RSendMessageBean(String vid, String uid, String content, String type) {
        this.vid = vid;
        this.uid = uid;
        this.content = content;
        this.type = type;
    }

    private String type;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
