package com.sence.bean.request.chat;

import com.sence.bean.base.BaseImageRequestBean;


/**
 * Created by zwy on 2019/4/10.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RSendPrivateImgMessageBean extends BaseImageRequestBean {
    public String getU_to() {
        return u_to;
    }

    public void setU_to(String u_to) {
        this.u_to = u_to;
    }

    private String u_to;
    private String uid;

    public RSendPrivateImgMessageBean(String u_to, String uid) {
        this.u_to = u_to;
        this.uid = uid;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
