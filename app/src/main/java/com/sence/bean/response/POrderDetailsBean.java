package com.sence.bean.response;

import java.util.List;

public class POrderDetailsBean {


    /**
     * id : 305
     * aid : 46
     * oid : 1915562484902989
     * addtime : 2019-04-26 11:14
     * status : 等待卖家发货
     * pmoney : 0
     * needpay : 599
     * gmoney : 599
     * cmoney : 0
     * address : {"username":"张三","phone":"18735610174","address":"东方福唐","area":"北京市北京市朝阳区","tel":"187****0174"}
     * orderStatus : 2
     * shopname : o～糖菓菓o_O
     * sellerUid : 19
     * goods : [{"price":"599","num":"1","id":"27","img":"/Public/Uploads/Goods/2019-04-23/5cbe8d9f8ec6c.png","name":"臭臭的香水打发士大夫的绯闻多多沟通的地方地方的"}]
     * fee : 0
     * custom : {"nick_name":"千山暮雪","avatar":"/Public/Uploads/header/2019-04-24/oH3Cg1Ac5YgO51cC-wPwPs6vd24Y.jpg","id":"28","name":"千山暮雪"}
     */

    private String id;
    private String aid;
    private String oid;
    private String addtime;
    private String status;
    private String pmoney;
    private String needpay;
    private String gmoney;
    private String cmoney;
    private AddressBean address;
    private String orderStatus;
    private String shopname;
    private String sellerUid;
    private String fee;
    private CustomBean custom;
    private List<GoodsBean> goods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPmoney() {
        return pmoney;
    }

    public void setPmoney(String pmoney) {
        this.pmoney = pmoney;
    }

    public String getNeedpay() {
        return needpay;
    }

    public void setNeedpay(String needpay) {
        this.needpay = needpay;
    }

    public String getGmoney() {
        return gmoney;
    }

    public void setGmoney(String gmoney) {
        this.gmoney = gmoney;
    }

    public String getCmoney() {
        return cmoney;
    }

    public void setCmoney(String cmoney) {
        this.cmoney = cmoney;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public CustomBean getCustom() {
        return custom;
    }

    public void setCustom(CustomBean custom) {
        this.custom = custom;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class AddressBean {
        /**
         * username : 张三
         * phone : 18735610174
         * address : 东方福唐
         * area : 北京市北京市朝阳区
         * tel : 187****0174
         */

        private String username;
        private String phone;
        private String address;
        private String area;
        private String tel;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }

    public static class CustomBean {
        /**
         * nick_name : 千山暮雪
         * avatar : /Public/Uploads/header/2019-04-24/oH3Cg1Ac5YgO51cC-wPwPs6vd24Y.jpg
         * id : 28
         * name : 千山暮雪
         */

        private String nick_name;
        private String avatar;
        private String id;
        private String name;

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
    }

    public static class GoodsBean {
        /**
         * price : 599
         * num : 1
         * id : 27
         * img : /Public/Uploads/Goods/2019-04-23/5cbe8d9f8ec6c.png
         * name : 臭臭的香水打发士大夫的绯闻多多沟通的地方地方的
         */

        private String price;
        private String num;
        private String id;
        private String img;
        private String name;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
