package com.sence.bean.response;

import java.util.List;

public class PServiceeDetails {


    /**
     * lng : 0
     * lat : 0
     * address :
     * isEvaluate : 1
     * id : 1
     * tag : 专业洗车券
     * imgs : ["/Public/Uploads/see_shop/4/5bfe2d5ed0b1f.jpg","/Public/Uploads/see_shop/4/5bfe2d5ed0f34.jpg","/Public/Uploads/see_shop/4/5bfe2d5ed12aa.jpg","/Public/Uploads/see_shop/4/5bfe2d5ed1548.jpg"]
     * sid : 1
     * position :
     * is_free : 0
     * username : hhhh
     * avatar : /Public/Home/img/default_avatar.png
     */

    private String lng;
    private String lat;
    private String address;
    private String isEvaluate;
    private String id;
    private String tag;
    private String sid;
    private String position;
    private String is_free;
    private String username;
    private String avatar;
    private List<String> imgs;

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsEvaluate() {
        return isEvaluate;
    }

    public void setIsEvaluate(String isEvaluate) {
        this.isEvaluate = isEvaluate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIs_free() {
        return is_free;
    }

    public void setIs_free(String is_free) {
        this.is_free = is_free;
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

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
