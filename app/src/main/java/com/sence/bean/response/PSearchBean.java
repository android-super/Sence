package com.sence.bean.response;

import java.util.List;

public class PSearchBean {

    private List<UserListBean> userList;
    private List<GoodsListBean> goodsList;

    public List<UserListBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListBean> userList) {
        this.userList = userList;
    }

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class UserListBean {
        /**
         * id : 1
         * avatar : /Public/Home/img/default_avatar.png
         * autograph :
         * username : 辣啦啦啦啦
         * isFollow : 0
         * isVip : 1
         */

        private String id;
        private String avatar;
        private String autograph;
        private String username;
        private String isFollow;
        private String isVip;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAutograph() {
            return autograph;
        }

        public void setAutograph(String autograph) {
            this.autograph = autograph;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(String isFollow) {
            this.isFollow = isFollow;
        }

        public String getIsVip() {
            return isVip;
        }

        public void setIsVip(String isVip) {
            this.isVip = isVip;
        }
    }

    public static class GoodsListBean {
        /**
         * id : 20
         * img : /Public/Uploads/Goods/2019-03-28/5c9c87c863804.jpg
         * name : [买一送一]麻辣小螃蟹香辣蟹大闸蟹零食罐装全母海鲜熟食即食醉蟹
         * price : 98
         * vprice : 49
         */

        private String id;
        private String img;
        private String name;
        private String price;
        private String vprice;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
