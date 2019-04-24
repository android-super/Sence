package com.sence.bean.request;

import java.util.List;

/**
 * Created by zwy on 2019/4/11.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RMemberBean {

    /**
     * is_join : 1
     * vgroup_name : 风散的聊天室
     * list : [{"uid":"4","user_name":"17600183077","nick_name":"风散",
     * "avatar":"/Public/Uploads/avatar/2019-04-02/5ca3479793176.jpg","details":"我的简介","autograph":"我改了个性签名哦",
     * "is_focus":"0","is_kol":"1","vid":"6","state":"0"}]
     */

    private String is_join;
    private String vgroup_name;
    private List<ListBean> list;

    public String getIs_join() {
        return is_join;
    }

    public void setIs_join(String is_join) {
        this.is_join = is_join;
    }

    public String getVgroup_name() {
        return vgroup_name;
    }

    public void setVgroup_name(String vgroup_name) {
        this.vgroup_name = vgroup_name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * uid : 4
         * user_name : 17600183077
         * nick_name : 风散
         * avatar : /Public/Uploads/avatar/2019-04-02/5ca3479793176.jpg
         * details : 我的简介
         * autograph : 我改了个性签名哦
         * is_focus : 0
         * is_kol : 1
         * vid : 6
         * state : 0
         */

        private String uid;
        private String user_name;
        private String nick_name;
        private String avatar;
        private String details;
        private String autograph;
        private String is_focus;
        private String is_kol;
        private String vid;
        private String state;
        private String is_service;

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

        public String getIs_focus() {
            return is_focus;
        }

        public void setIs_focus(String is_focus) {
            this.is_focus = is_focus;
        }

        public String getIs_kol() {
            return is_kol;
        }

        public void setIs_kol(String is_kol) {
            this.is_kol = is_kol;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getIs_service() {
            return is_service;
        }

        public void setIs_service(String is_service) {
            this.is_service = is_service;
        }
    }
}
