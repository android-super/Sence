package com.sence.bean.response;

import java.util.List;

/**
 * Created by zwy on 2019/3/30.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PContentDetailBean {

    /**
     * note_info : {"nid":"2","uid":"999","title":"我是标题！",
     * "content":"aaaa啊asdasdasda阿萨德发的阿萨达阿萨达阿萨达阿萨达阿萨达啊实打实大阿萨达阿萨达啊","click_num":"5","add_time":"2019-03-14 17:03:33",
     * "user_name":"17600183077","nick_name":"风散","avatar":"/Public/Uploads/avatar/2019-03-20/5c91ef01ad1ae.jpg",
     * "details":"我的简介","autograph":"我的个性签名","albums":[{"id":"3","album_url":"/Public/Uploads/albums/2019-03-14
     * /5c8a18e55dc8e.jpg","tags":"{1:1}"}],"praise_count":"0","message_count":"2","goods_info":[{"name":"虹越园艺家
     * 欧洲藤本月季花苗木 龙沙宝石1.5年嫁接盆栽苗 包邮","img":"/Public/Uploads/Goods/2019-03-27/5c9afaf63f8b8.jpg","id":"1","price":"50",
     * "vprice":"40"}]}
     */

    private NoteInfoBean note_info;

    public NoteInfoBean getNote_info() {
        return note_info;
    }

    public void setNote_info(NoteInfoBean note_info) {
        this.note_info = note_info;
    }

    public static class NoteInfoBean {
        /**
         * nid : 2
         * uid : 999
         * title : 我是标题！
         * content : aaaa啊asdasdasda阿萨德发的阿萨达阿萨达阿萨达阿萨达阿萨达啊实打实大阿萨达阿萨达啊
         * click_num : 5
         * add_time : 2019-03-14 17:03:33
         * user_name : 17600183077
         * nick_name : 风散
         * avatar : /Public/Uploads/avatar/2019-03-20/5c91ef01ad1ae.jpg
         * details : 我的简介
         * autograph : 我的个性签名
         * albums : [{"id":"3","album_url":"/Public/Uploads/albums/2019-03-14/5c8a18e55dc8e.jpg","tags":"{1:1}"}]
         * praise_count : 0
         * message_count : 2
         * goods_info : [{"name":"虹越园艺家 欧洲藤本月季花苗木 龙沙宝石1.5年嫁接盆栽苗 包邮",
         * "img":"/Public/Uploads/Goods/2019-03-27/5c9afaf63f8b8.jpg","id":"1","price":"50","vprice":"40"}]
         */

        private String nid;
        private String uid;
        private String title;
        private String content;
        private String click_num;
        private String add_time;
        private String user_name;
        private String nick_name;
        private String avatar;
        private String details;
        private String autograph;
        private String praise_count;
        private String message_count;
        private List<AlbumsBean> albums;
        private List<GoodsInfoBean> goods_info;

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

        public List<GoodsInfoBean> getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(List<GoodsInfoBean> goods_info) {
            this.goods_info = goods_info;
        }

        public static class AlbumsBean {
            /**
             * id : 3
             * album_url : /Public/Uploads/albums/2019-03-14/5c8a18e55dc8e.jpg
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

        public static class GoodsInfoBean {
            /**
             * name : 虹越园艺家 欧洲藤本月季花苗木 龙沙宝石1.5年嫁接盆栽苗 包邮
             * img : /Public/Uploads/Goods/2019-03-27/5c9afaf63f8b8.jpg
             * id : 1
             * price : 50
             * vprice : 40
             */

            private String name;
            private String img;
            private String id;
            private String price;
            private String vprice;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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
        }
    }
}
