package com.sence.activity.chat.bean;

public class ChatSocketBean {

    /**
     * type : vgroup
     * data : {"code":"100000","uid":"11","avatar":"/Public/Home/img/default_avatar.png","nick_name":"哈家",
     * "content":"图片","type":"2","img":"/Public/Uploads/VGroupChat/2019-04-26/5cc2e9f164015.jpg"}
     */

    private String type;
    private String client_id;
    private DataBean data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public static class DataBean {
        /**
         * code : 100000
         * uid : 11
         * avatar : /Public/Home/img/default_avatar.png
         * nick_name : 哈家
         * content : 图片
         * type : 2
         * img : /Public/Uploads/VGroupChat/2019-04-26/5cc2e9f164015.jpg
         */

        private String code;
        private String uid;
        private String avatar;
        private String nick_name;
        private String content;
        private int type;
        private String img;
        private long add_time;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }
    }
}
