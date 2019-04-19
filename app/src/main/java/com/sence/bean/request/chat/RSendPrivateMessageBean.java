package com.sence.bean.request.chat;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/10.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RSendPrivateMessageBean extends BaseRequestBean {
    private String u_to;
    private String uid;
    private String content;

    public RSendPrivateMessageBean(String u_to, String uid, String content) {
        this.u_to = u_to;
        this.uid = uid;
        this.content = content;
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

    public String getU_to() {
        return u_to;
    }

    public void setU_to(String u_to) {
        this.u_to = u_to;
    }
}
