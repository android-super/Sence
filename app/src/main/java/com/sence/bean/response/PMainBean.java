package com.sence.bean.response;

import java.util.List;

/**
 * Created by zwy on 2019/4/12.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PMainBean {

    private List<NoteListBean> note_list;
    private List<GoodessRecommendBean> goodess_recommend;

    public List<NoteListBean> getNote_list() {
        return note_list;
    }

    public void setNote_list(List<NoteListBean> note_list) {
        this.note_list = note_list;
    }

    public List<GoodessRecommendBean> getGoodess_recommend() {
        return goodess_recommend;
    }

    public void setGoodess_recommend(List<GoodessRecommendBean> goodess_recommend) {
        this.goodess_recommend = goodess_recommend;
    }

    public static class NoteListBean {
        /**
         * nid : 3
         * uid : 4
         * content : 测试发布我的笔记
         * click_num : 149
         * type : 2
         * add_time : 2019.03.15 11:51
         * avatar : /Public/Uploads/avatar/2019-04-02/5ca3479793176.jpg
         * user_name : 17600183077
         * nick_name : 风散
         * album_url : /Public/Uploads/albums/2019-03-15/5c8b215d2087d.jpg
         * details :
         * is_kol : 1
         * is_focus : 0
         * vid : 5
         * message_count : 0
         * praise_count : 4
         * is_like : 1
         */
        private String title;
        private String nid;
        private String uid;
        private String content;
        private String note_type;
        private String click_num;
        private String type;
        private String add_time;
        private String avatar;
        private String user_name;
        private String nick_name;
        private String album_url;
        private String details;
        private String is_kol;
        private String is_focus;
        private String vid;
        private String message_count;
        private String praise_count;
        private String is_like;

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        private float height;
        private float width;

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        private String describe;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNote_type() {
            return note_type;
        }

        public void setNote_type(String note_type) {
            this.note_type = note_type;
        }
    }

    public static class GoodessRecommendBean {
        /**
         * nid : 3
         * uid : 4
         * content : 测试发布我的笔记
         * click_num : 140
         * type : 2
         * add_time : 2019.03.15 11:51
         * avatar : /Public/Uploads/avatar/2019-04-02/5ca3479793176.jpg
         * user_name : 17600183077
         * nick_name : 风散
         * album_url : /Public/Uploads/albums/2019-03-15/5c8b215d2087d.jpg
         * details :
         * is_kol : 1
         * is_focus : 0
         * vid : 5
         * message_count : 0
         * praise_count : 4
         * is_like : 0
         */

        private String nid;
        private String uid;
        private String content;
        private String click_num;
        private String type;
        private String add_time;
        private String avatar;
        private String user_name;
        private String nick_name;
        private String album_url;
        private String details;
        private String is_kol;
        private String is_focus;
        private String vid;
        private String message_count;
        private String praise_count;
        private String is_like;

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
