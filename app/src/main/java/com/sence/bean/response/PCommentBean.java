package com.sence.bean.response;

/**
 * Created by zwy on 2019/3/30.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PCommentBean {

    /**
     * id : 2
     * rid : 2
     * p_uid : 999
     * uid : 4
     * content : 测试评论
     * add_time : 1552639968
     * pid : 1
     * state : 0
     * user : {"id":"4","user_name":"17600183077","nick_name":"风散","avatar":""}
     * reply : {"id":"4","user_name":"17600183077","nick_name":"风散","avatar":""}
     * now_time : 1552642596
     * diff_time : 2628
     * is_like : 0
     * like_num : 1
     * old_message : 测试评论
     */

    private String id;
    private String rid;
    private String p_uid;
    private String uid;
    private String content;
    private String add_time;
    private String pid;
    private String state;
    private UserBean user;
    private ReplyBean reply;
    private String now_time;
    private String diff_time;
    private String is_like;
    private String like_num;
    private String old_message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getP_uid() {
        return p_uid;
    }

    public void setP_uid(String p_uid) {
        this.p_uid = p_uid;
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

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public ReplyBean getReply() {
        return reply;
    }

    public void setReply(ReplyBean reply) {
        this.reply = reply;
    }

    public String getNow_time() {
        return now_time;
    }

    public void setNow_time(String now_time) {
        this.now_time = now_time;
    }

    public String getDiff_time() {
        return diff_time;
    }

    public void setDiff_time(String diff_time) {
        this.diff_time = diff_time;
    }

    public String getIs_like() {
        return is_like;
    }

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getOld_message() {
        return old_message;
    }

    public void setOld_message(String old_message) {
        this.old_message = old_message;
    }

    public static class UserBean {
        /**
         * id : 4
         * user_name : 17600183077
         * nick_name : 风散
         * avatar :
         */

        private String id;
        private String user_name;
        private String nick_name;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }

    public static class ReplyBean {
        /**
         * id : 4
         * user_name : 17600183077
         * nick_name : 风散
         * avatar :
         */

        private String id;
        private String user_name;
        private String nick_name;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }
}
