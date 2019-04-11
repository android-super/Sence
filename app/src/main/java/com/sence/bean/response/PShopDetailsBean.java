package com.sence.bean.response;

import java.util.List;

public class PShopDetailsBean {


    /**
     * img : /Public/Uploads/Goods/2019-03-27/5c9afaf63f8b8.jpg
     * postage : 6
     * imgs : ["/Public/Uploads/see_shop/4/5bfe2d5ed0b1f.jpg","/Public/Uploads/see_shop/4/5bfe2d5ed0f34.jpg","/Public/Uploads/see_shop/4/5bfe2d5ed12aa.jpg","/Public/Uploads/see_shop/4/5bfe2d5ed1548.jpg"]
     * name : 一号商品
     * describe : http://192.168.1.10:8081/index.php/Shares/Public/show_notice_info/6
     * price : 10
     * vprice : 5
     * uid : 1
     * discount : 5
     * username : hhhh
     * avatar :
     * cartNum : 0
     * customId : 1
     * customName : hhhh
     * customAvatar :
     * commentNum : 2903
     * commentRate : 97.7
     * comment : [{"nickname":"我是昵称","avatar":"我应该是头像地址","img":"我是评价的图片地址","content":"我是评价的内容"},{"nickname":"我是昵称2","avatar":"我应该是头像地址2","img":"我是评价的图片地址2","content":"我是评价的内容2"},{"nickname":"我是昵称3","avatar":"我应该是头像地址3","img":"我是评价的图片地址3","content":"我是评价的内容3"}]
     */

    private String img;
    private String postage;
    private String name;
    private String describe;
    private String price;
    private String vprice;
    private String uid;
    private String discount;
    private String username;
    private String avatar;
    private String cartNum;
    private String customId;
    private String customName;
    private String customAvatar;
    private String commentNum;
    private String commentRate;
    private List<String> imgs;
    private List<CommentBean> comment;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPostage() {
        return postage;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVprice() {
        return vprice;
    }

    public void setVprice(String vprice) {
        this.vprice = vprice;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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

    public String getCartNum() {
        return cartNum;
    }

    public void setCartNum(String cartNum) {
        this.cartNum = cartNum;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomAvatar() {
        return customAvatar;
    }

    public void setCustomAvatar(String customAvatar) {
        this.customAvatar = customAvatar;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getCommentRate() {
        return commentRate;
    }

    public void setCommentRate(String commentRate) {
        this.commentRate = commentRate;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }

    public static class CommentBean {
        /**
         * nickname : 我是昵称
         * avatar : 我应该是头像地址
         * img : 我是评价的图片地址
         * content : 我是评价的内容
         */

        private String nickname;
        private String avatar;
        private String img;
        private String content;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
