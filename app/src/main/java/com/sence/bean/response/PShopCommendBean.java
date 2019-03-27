package com.sence.bean.response;

import java.util.List;

public class PShopCommendBean {

    /**
     * content : 好阿红啊好好哦啊哈佛安徽分
     * id : 2
     * nickname : hhhh
     * avatar : /Public/Home/img/default_avatar.png
     * imgs : ["/Public/Uploads/Order/2019-03-26//5c998eba14438.jpg","/Public/Uploads/Order/2019-03-26//5c998eba153d8.jpg"]
     */

    private String content;
    private String id;
    private String nickname;
    private String avatar;
    private List<String> imgs;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
