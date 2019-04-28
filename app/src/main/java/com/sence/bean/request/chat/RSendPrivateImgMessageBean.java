package com.sence.bean.request.chat;

import com.sence.bean.base.BaseImageRequestBean;


/**
 * Created by zwy on 2019/4/10.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RSendPrivateImgMessageBean extends BaseImageRequestBean {

    private String to_uid;
    private String uid;

    public RSendPrivateImgMessageBean(String to_uid, String uid) {
        this.to_uid = to_uid;
        this.uid = uid;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }
}
