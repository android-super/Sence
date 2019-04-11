package com.sence.bean.response;

import java.util.List;

public class PMyInfoBean {

    /**
     * uid : 4
     * user_name : 17600183077
     * nick_name : 风散
     * avatar : /Public/Uploads/avatar/2019-03-20/5c91ef01ad1ae.jpg
     * details : 我的简介
     * autograph : 我的个性
     * focus_num : 1
     * fans_num : 1
     * is_kol : 1
     * is_focus : 0
     * vid :
     * is_read : 1
     * goods_info : {"id":"2","name":"2号商品","introduce":"方法大放丁放2","describe":"房辅导班女孩33333给能换个你","img":"Public/Uploads/Order/2019-03-26//5c99eb1fc12a5.jpg","price":"4.00"}
     * other_info : [{"nid":"2","uid":"4","title":"我是标题！","content":"aaaa啊asdasdasda阿萨德发的阿萨达阿萨达阿萨达阿萨达阿萨达啊实打实大阿萨达阿萨达啊","click_num":"5","type":"1","avatar":"/Public/Uploads/avatar/2019-03-20/5c91ef01ad1ae.jpg","user_name":"17600183077","nick_name":"风散","album_url":"/Public/Uploads/albums/2019-03-14/5c8a18e55dc8e.jpg","is_kol":"1","message_count":"2","praise_count":"0","is_like":"0","add_time":"2019.03.14 17:03"}]
     */

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
    private List<OtherInfoBean> other_info;

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

    public List<OtherInfoBean> getOther_info() {
        return other_info;
    }

    public void setOther_info(List<OtherInfoBean> other_info) {
        this.other_info = other_info;
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

    public static class OtherInfoBean {
        /**
         * nid : 2
         * uid : 4
         * title : 我是标题！
         * content : aaaa啊asdasdasda阿萨德发的阿萨达阿萨达阿萨达阿萨达阿萨达啊实打实大阿萨达阿萨达啊
         * click_num : 5
         * type : 1
         * avatar : /Public/Uploads/avatar/2019-03-20/5c91ef01ad1ae.jpg
         * user_name : 17600183077
         * nick_name : 风散
         * album_url : /Public/Uploads/albums/2019-03-14/5c8a18e55dc8e.jpg
         * is_kol : 1
         * message_count : 2
         * praise_count : 0
         * is_like : 0
         * add_time : 2019.03.14 17:03
         */

        private String nid;
        private String uid;
        private String title;
        private String content;
        private String click_num;
        private String type;
        private String avatar;
        private String user_name;
        private String nick_name;
        private String album_url;
        private String is_kol;
        private String message_count;
        private String praise_count;
        private String is_like;
        private String add_time;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getAlbum_url() {
            return album_url;
        }

        public void setAlbum_url(String album_url) {
            this.album_url = album_url;
        }

        public String getIs_kol() {
            return is_kol;
        }

        public void setIs_kol(String is_kol) {
            this.is_kol = is_kol;
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

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
