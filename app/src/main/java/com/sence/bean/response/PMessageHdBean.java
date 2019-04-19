package com.sence.bean.response;

public class PMessageHdBean {

    /**
     * nid : 2
     * content : 测试评论
     * add_time : 评论了你的作品 03-15
     * uid : 3
     * user_name : 17600183078
     * nick_name : 176****3078
     * avatar : /Public/Home/img/default_avatar.png
     * note_type : 2
     * album_url : /Public/Uploads/albums/2019-03-14/5c8a18e55dc8e.jpg
     */

    private String nid;
    private String content;
    private String add_time;
    private String uid;
    private String user_name;
    private String nick_name;
    private String avatar;
    private String note_type;
    private String album_url;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
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

    public String getNote_type() {
        return note_type;
    }

    public void setNote_type(String note_type) {
        this.note_type = note_type;
    }

    public String getAlbum_url() {
        return album_url;
    }

    public void setAlbum_url(String album_url) {
        this.album_url = album_url;
    }
}
