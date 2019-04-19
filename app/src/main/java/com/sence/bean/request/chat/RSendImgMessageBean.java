package com.sence.bean.request.chat;

import com.sence.bean.base.BaseImageRequestBean;


/**
 * Created by zwy on 2019/4/10.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RSendImgMessageBean extends BaseImageRequestBean {
    private String vid;
    private String uid;
    private String type;

    public RSendImgMessageBean(String vid, String uid, String type) {
        this.vid = vid;
        this.uid = uid;
        this.type = type;
    }

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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
