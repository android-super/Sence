package com.sence.bean.response;

import java.io.Serializable;
import java.util.List;

public class PShopDetailsBean {


    /**
     * status : 1
     * imgUrl : http://192.168.1.10:8085//Public/Uploads/Goods/2019-03-27/5c9afaf63f8b8.jpg
     * favourable : 1
     * isMember :
     * id : 1
     * img : /Public/Uploads/Goods/2019-03-27/5c9afaf63f8b8.jpg
     * postage : 6
     * imgs : [{"type":"2","img":"/Public/Uploads/Goods/2019-06-03/5cf4e8653cee1.jpg","video":"/Public/Uploads/Goods/2019-06-03/5cf4e865439f2.mp4"},{"type":"1","img":"/Public/Uploads/Goods/2019-06-03/5cf4e86546810.jpg","video":""},{"type":"1","img":"/Public/Uploads/Goods/2019-06-03/5cf4e86547ea7.jpg","video":""}]
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
     * comment : {"content":"天通苑南不阿妈啦呼呼呼呼呼呼11","imgs":["/Public/Uploads/Order/2019-04-18//5cb7f60e54c4b.jpg","/Public/Uploads/Order/2019-04-18//5cb7f60e556df.jpg","/Public/Uploads/Order/2019-04-18//5cb7f60e56016.jpg"],"id":"30","praise":"0","addtime":"2019-04-18","nickname":"辣啦啦啦啦","avatar":"/Public/Uploads/avatar/2019-04-13/5cb1d167ca300.jpg","isPraise":"0"}
     */

    private String status;
    private String imgUrl;
    private String favourable;
    private String isMember;
    private String id;
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
    private CommentBean comment;
    private List<ImgsBean> imgs;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFavourable() {
        return favourable;
    }

    public void setFavourable(String favourable) {
        this.favourable = favourable;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public static class CommentBean {
        /**
         * content : 天通苑南不阿妈啦呼呼呼呼呼呼11
         * imgs : ["/Public/Uploads/Order/2019-04-18//5cb7f60e54c4b.jpg","/Public/Uploads/Order/2019-04-18//5cb7f60e556df.jpg","/Public/Uploads/Order/2019-04-18//5cb7f60e56016.jpg"]
         * id : 30
         * praise : 0
         * addtime : 2019-04-18
         * nickname : 辣啦啦啦啦
         * avatar : /Public/Uploads/avatar/2019-04-13/5cb1d167ca300.jpg
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

    public static class ImgsBean implements Serializable {
        /**
         * type : 2
         * img : /Public/Uploads/Goods/2019-06-03/5cf4e8653cee1.jpg
         * video : /Public/Uploads/Goods/2019-06-03/5cf4e865439f2.mp4
         */

        private String type;
        private String img;
        private String video;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }
    }
}
