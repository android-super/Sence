package com.sence.bean.response;

import java.util.List;

public class PMyInfoBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * nid : 1
         * uid : 3
         * title :
         * describe :
         * content : aaaa啊啊啊
         * click_num : 185
         * type : 2
         * add_time : 2019.03.14 17:03
         * avatar : /Public/Home/img/default_avatar.png
         * user_name : 17600183078
         * nick_name : 176****3078
         * album_url : /Public/Uploads/albums/2019-03-14/5c8a18e55b13d.jpg
         * width : 111
         * height : 222
         * details :
         * is_kol : 1
         * is_focus : 0
         * vid :
         * message_count : 2
         * praise_count : 3
         * is_like : 0
         */

        private String nid;
        private String uid;
        private String title;
        private String describe;
        private String content;
        private String click_num;
        private String type;
        private String add_time;
        private String avatar;
        private String user_name;
        private String nick_name;
        private String album_url;
        private float width;
        private float height;
        private String details;
        private String is_kol;
        private String is_focus;
        private String vid;
        private String message_count;

        public String getNote_type() {
            return note_type;
        }

        public void setNote_type(String note_type) {
            this.note_type = note_type;
        }

        private String praise_count;
        private String is_like;
        private String note_type;
        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getClick_num() {
            return click_num;
        }

        public void setClick_num(String click_num) {
            this.click_num = click_num;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public String getAlbum_url() {
            return album_url;
        }

        public void setAlbum_url(String album_url) {
            this.album_url = album_url;
        }

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
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

        public String getMessage_count() {
            return message_count;
        }

        public void setMessage_count(String message_count) {
            this.message_count = message_count;
        }

        public String getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(String praise_count) {
            this.praise_count = praise_count;
        }

        public String getIs_like() {
            return is_like;
        }

        public void setIs_like(String is_like) {
            this.is_like = is_like;
        }
    }
}
