package com.sence.bean.response;

import java.util.List;

public class PServiceCommendBean {

    /**
     * id : 3
     * uid : 1
     * star : 1
     * content : 好阿红啊好好哦啊哈佛安徽分
     * imgs : ["/Public/Uploads/Order/2019-03-26//5c99910ca6bf8.jpg","/Public/Uploads/Order/2019-03-26//5c99910ca7b98.jpg"]
     * username : hhhh
     * avatar : /Public/Home/img/default_avatar.png
     * time : 3月26日
     */

    private String id;
    private String uid;
    private String star;
    private String content;
    private String username;
    private String avatar;
    private String time;
    private List<String> imgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
