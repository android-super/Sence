package com.sence.bean.response;

import java.util.List;

/**
 * Created by zwy on 2019/4/10.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PChatMessageBean {

    /**
     * vgroup_name : 风散的聊天室
     * message : [{"id":"1","content":"啊实打实","type":"1","add_time":"2019-04-01 16:08:01","uid":"4",
     * "user_name":"17600183077","nick_name":"风散","avatar":"/Public/Uploads/avatar/2019-04-02/5ca3479793176.jpg",
     * "details":"","is_kol":"1","is_focus":"0","vid":"1"}]
     */

    private String vgroup_name;
    private List<MessageBean> message;

    public String getVgroup_name() {
        return vgroup_name;
    }

    public void setVgroup_name(String vgroup_name) {
        this.vgroup_name = vgroup_name;
    }

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * id : 1
         * content : 啊实打实
         * type : 1
         * add_time : 2019-04-01 16:08:01
         * uid : 4
         * user_name : 17600183077
         * nick_name : 风散
         * avatar : /Public/Uploads/avatar/2019-04-02/5ca3479793176.jpg
         * details :
         * is_kol : 1
         * is_focus : 0
         * vid : 1
         */

        private String id;
        private String content;
        private int type;
        private long add_time;
        private String uid;
        private String user_name;
        private String nick_name;
        private String avatar;
        private String details;
        private String is_kol;
        private String is_focus;
        private String vid;
        private boolean isTimeVisible = false;//计算每条消息是否显示时间

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

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

        public boolean isTimeVisible() {
            return isTimeVisible;
        }

        public void setTimeVisible(boolean timeVisible) {
            isTimeVisible = timeVisible;
        }
    }
}
