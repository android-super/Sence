package com.sence.bean.request.chat;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/24.
 * package_name is com.sence.bean.request.chat
 * 描述:SenceGit
 */
public class RUtoVidBean extends BaseRequestBean {
    private String uid;
    private String vid;

    public RUtoVidBean(String uid, String vid, String to_uid) {
        this.uid = uid;
        this.vid = vid;
        this.to_uid = to_uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }

    private String to_uid;
}
