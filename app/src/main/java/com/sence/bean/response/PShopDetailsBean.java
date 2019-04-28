package com.sence.bean.response;

import java.util.List;

public class PShopDetailsBean {

    /**
     * imgs : ["/Public/Uploads/Goods/2019-04-23/5cbe8b5ddc05c.jpg","/Public/Uploads/Goods/2019-04-23/5cbe8b5ddc65f.jpg"]
     * name : 2019最新款耳机机构行家有同居体育等法规和小伙伴幸福感
     * describe : http://sence.forhour.com//index.php/Api/Goods/getGoodsHtml/23
     * price : 650
     * vprice : 630
     * uid : 19
     * img : /Public/Uploads/Goods/2019-04-23/5cbe8b5ddb8db.png
     * postage : 0
     * id : 23
     * subhead :
     * favourable : 0.00
     * discount : 9.7
     * username : o～糖菓菓o_O
     * avatar : /Public/Uploads/header/2019-04-22/oH3Cg1N6vRkmU8vZ8RSI0CPgUc6U.jpg
     * cartNum : 7
     * customId : 28
     * customName : 千山暮雪
     * customAvatar : /Public/Uploads/avatar/2019-04-26/5cc27f58b6575.jpg
     * commentNum : 1
     * commentRate : 100
     * comment : {"content":"来咯呕吐都来了","imgs":[],"id":"37","praise":"1","addtime":"2019-04-25","nickname":"相濡以沫ふ","avatar":"/Public/Uploads/header/2019-04-23/oH3Cg1Pzkv_GmcFkRUkKVQHVmgl4.jpg","isPraise":"0"}
     * isMember : 1
     * imgUrl : http://sence.forhour.com//Public/Uploads/Goods/2019-04-23/5cbe8b5ddb8db.png
     */
    private String status;
    private String name;
    private String describe;
    private String price;
    private String vprice;
    private String uid;
    private String img;
    private String postage;
    private String id;
    private String subhead;
    private String favourable;
    private String discount;
    private String username;
    private String avatar;
    private String cartNum;
    private String customId;
    private String customName;
    private String customAvatar;
    private String commentNum;
    private String commentRate;
    private CommentBean comment;
    private String isMember;
    private String imgUrl;
    private List<String> imgs;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getFavourable() {
        return favourable;
    }

    public void setFavourable(String favourable) {
        this.favourable = favourable;
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

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public static class CommentBean {
        /**
         * content : 来咯呕吐都来了
         * imgs : []
         * id : 37
         * praise : 1
         * addtime : 2019-04-25
         * nickname : 相濡以沫ふ
         * avatar : /Public/Uploads/header/2019-04-23/oH3Cg1Pzkv_GmcFkRUkKVQHVmgl4.jpg
         * isPraise : 0
         */

        private String content;
        private String id;
        private String praise;
        private String addtime;
        private String nickname;
        private String avatar;
        private String isPraise;
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

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
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

        public String getIsPraise() {
            return isPraise;
        }

        public void setIsPraise(String isPraise) {
            this.isPraise = isPraise;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
