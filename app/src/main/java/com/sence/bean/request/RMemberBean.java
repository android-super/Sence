package com.sence.bean.request;

/**
 * Created by zwy on 2019/4/11.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RMemberBean {

    /**
     * uid : 1
     * user_name : 18500121621
     * nick_name : 辣啦啦啦啦
     * avatar : /Public/Home/img/default_avatar.png
     * details :
     * autograph :
     * is_kol : 1
     * is_focus : 1
     * vid :
     */

    private String uid;
    private String user_name;
    private String nick_name;
    private String avatar;
    private String details;
    private String autograph;
    private String is_kol;
    private String is_focus;
    private String vid;

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

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}
