package com.sence.bean.response;

public class PUserMyInfoBean {

    /**
     * uid : 4
     * user_name : 17600183077
     * nick_name : 风散
     * avatar : /Public/Uploads/avatar/2019-03-20/5c91ef01ad1ae.jpg
     * details : 我的简介
     * autograph : 我的个性签名
     * focus_num : 1
     * fans_num : 1
     * is_kol : 1
     * is_focus : 0
     * vid :
     * is_read : 1
     * goods_info : {"id":"2","name":"2号商品","introduce":"方法大放丁放2","describe":"房辅导班女孩33333给能换个你","img":"Public/Uploads/Order/2019-03-26//5c99eb1fc12a5.jpg","price":"4.00"}
     * is_have_service : 1
     */
    private String inviteCode;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    private String uid;
    private String user_name;
    private String nick_name;
    private String avatar;
    private String details;
    private String autograph;
    private String focus_num;
    private String fans_num;
    private String is_kol;
    private String is_focus;
    private String vid;
    private String is_read;
    private GoodsInfoBean goods_info;
    private String is_have_service;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getFocus_num() {
        return focus_num;
    }

    public void setFocus_num(String focus_num) {
        this.focus_num = focus_num;
    }

    public String getFans_num() {
        return fans_num;
    }

    public void setFans_num(String fans_num) {
        this.fans_num = fans_num;
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

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    public GoodsInfoBean getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(GoodsInfoBean goods_info) {
        this.goods_info = goods_info;
    }

    public String getIs_have_service() {
        return is_have_service;
    }

    public void setIs_have_service(String is_have_service) {
        this.is_have_service = is_have_service;
    }

    public static class GoodsInfoBean {
        /**
         * id : 2
         * name : 2号商品
         * introduce : 方法大放丁放2
         * describe : 房辅导班女孩33333给能换个你
         * img : Public/Uploads/Order/2019-03-26//5c99eb1fc12a5.jpg
         * price : 4.00
         */

        private String id;
        private String name;
        private String introduce;
        private String describe;
        private String img;
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
