package com.sence.bean.request;

import com.sence.bean.base.BaseImageRequestBean;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RUserEditBean extends BaseImageRequestBean {
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String uid;
    private String nick_name;
    private String autograph;
    private String sex;

    public RUserEditBean(String uid, String nick_name, String autograph, String sex, String avatar) {
        this.uid = uid;
        this.nick_name = nick_name;
        this.autograph = autograph;
        this.sex = sex;
        this.avatar = avatar;
    }

    private String avatar;
}
