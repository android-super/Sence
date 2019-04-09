package com.sence.bean.response;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PFansBean {

    /**
     * uid : 4
     * user_name : 17600183077
     * nick_name : 风散
     * avatar : /Public/Uploads/avatar/2019-03-20/5c91ef01ad1ae.jpg
     * details : 我的简介
     * autograph : 我的个性签名
     * is_kol : 1
     * is_focus : 0
     */

    private String uid;
    private String user_name;
    private String nick_name;
    private String avatar;
    private String details;
    private String autograph;
    private String is_kol;
    private String is_focus;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getIs_kol() {
        return is_kol;
    }

    public void setIs_kol(String is_kol) {
        this.is_kol = is_kol;
    }

    public String getIs_focus() {
        return is_focus;
    }

    public void setIs_focus(String is_focus) {
        this.is_focus = is_focus;
    }
}
