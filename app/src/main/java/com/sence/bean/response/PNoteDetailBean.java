package com.sence.bean.response;

import java.util.List;

/**
 * Created by zwy on 2019/3/29.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PNoteDetailBean {

    /**
     * note_info : {"nid":"1","uid":"3","content":"aaaa啊啊啊","click_num":"18","add_time":"2019-03-14 17:03:33",
     * "user_name":"17600183078","nick_name":"176****3078","avatar":"/Public/Home/img/default_avatar.png","albums":[{
     * "id":"1","album_url":"/Public/Uploads/albums/2019-03-14/5c8a18e55b13d.jpg","tags":"{1:1}"},{"id":"2",
     * "album_url":"/Public/Uploads/albums/2019-03-14/5c8a18e55ca90.jpg","tags":"{1:1}"}],"praise_count":"1",
     * "message_count":"2"}
     * recommend_note : [{"nid":"3","uid":"4","content":"测试发布我的笔记","click_num":"0","type":"2",
     * "avatar":"/Public/Uploads/avatar/2019-03-20/5c91ef01ad1ae.jpg","user_name":"17600183077","nick_name":"风散",
     * "praise_count":"2","album_url":"/Public/Uploads/albums/2019-03-15/5c8b215d2087d.jpg","is_like":"1"},{"nid":"1
     * ","uid":"3","content":"aaaa啊啊啊","click_num":"19","type":"2","avatar":"/Public/Home/img/default_avatar.png",
     * "user_name":"17600183078","nick_name":"176****3078","praise_count":"1",
     * "album_url":"/Public/Uploads/albums/2019-03-14/5c8a18e55b13d.jpg","is_like":"1"}]
     */

    private NoteInfoBean note_info;
    private List<RecommendNoteBean> recommend_note;

    public NoteInfoBean getNote_info() {
        return note_info;
    }

    public void setNote_info(NoteInfoBean note_info) {
        this.note_info = note_info;
    }

    public List<RecommendNoteBean> getRecommend_note() {
        return recommend_note;
    }

    public void setRecommend_note(List<RecommendNoteBean> recommend_note) {
        this.recommend_note = recommend_note;
    }

    public static class NoteInfoBean {
        /**
         * nid : 1
         * uid : 3
         * content : aaaa啊啊啊
         * click_num : 18
         * add_time : 2019-03-14 17:03:33
         * user_name : 17600183078
         * nick_name : 176****3078
         * avatar : /Public/Home/img/default_avatar.png
         * albums : [{"id":"1","album_url":"/Public/Uploads/albums/2019-03-14/5c8a18e55b13d.jpg","tags":"{1:1}"},{"id
         * ":"2","album_url":"/Public/Uploads/albums/2019-03-14/5c8a18e55ca90.jpg","tags":"{1:1}"}]
         * praise_count : 1
         * message_count : 2
         */

        private String nid;
        private String uid;
        private String content;
        private String click_num;
        private String add_time;
        private String user_name;
        private String nick_name;
        private String avatar;
        private String praise_count;
        private String message_count;
        private List<AlbumsBean> albums;

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

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
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

        public String getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(String praise_count) {
            this.praise_count = praise_count;
        }

        public String getMessage_count() {
            return message_count;
        }

        public void setMessage_count(String message_count) {
            this.message_count = message_count;
        }

        public List<AlbumsBean> getAlbums() {
            return albums;
        }

        public void setAlbums(List<AlbumsBean> albums) {
            this.albums = albums;
        }

        public static class AlbumsBean {
            /**
             * id : 1
             * album_url : /Public/Uploads/albums/2019-03-14/5c8a18e55b13d.jpg
             * tags : {1:1}
             */

            private String id;
            private String album_url;
            private String tags;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAlbum_url() {
                return album_url;
            }

            public void setAlbum_url(String album_url) {
                this.album_url = album_url;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }
        }
    }

    public static class RecommendNoteBean {
        /**
         * nid : 3
         * uid : 4
         * content : 测试发布我的笔记
         * click_num : 0
         * type : 2
         * avatar : /Public/Uploads/avatar/2019-03-20/5c91ef01ad1ae.jpg
         * user_name : 17600183077
         * nick_name : 风散
         * praise_count : 2
         * album_url : /Public/Uploads/albums/2019-03-15/5c8b215d2087d.jpg
         * is_like : 1
         */

        private String nid;
        private String uid;
        private String content;
        private String click_num;
        private String type;
        private String avatar;
        private String user_name;
        private String nick_name;
        private String praise_count;
        private String album_url;
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

        public String getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(String praise_count) {
            this.praise_count = praise_count;
        }

        public String getAlbum_url() {
            return album_url;
        }

        public void setAlbum_url(String album_url) {
            this.album_url = album_url;
        }

        public String getIs_like() {
            return is_like;
        }

        public void setIs_like(String is_like) {
            this.is_like = is_like;
        }
    }
}
