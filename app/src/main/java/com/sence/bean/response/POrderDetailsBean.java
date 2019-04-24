package com.sence.bean.response;

import java.util.List;

public class POrderDetailsBean {


    /**
     * id : 1
     * aid : 1
     * oid : 1
     * addtime : 0000-00-00 00:00:00
     * status : 等待买家付款
     * cmoney : 0.00
     * pmoney : 0.00
     * needpay : 33.00
     * gmoney : 0.00
     * address : {"username":"","phone":"","address":"11111","area":""}
     * shopname : 风散
     * goods : [{"price":"12","id":"2","img":"Public/Uploads/Order/2019-03-26//5c99eb1fc12a5.jpg","name":"2号商品","num":"3"}]
     * fee : 0
     */
    private String customId;
    private String customName;
    private String customAvatar;

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

    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    private String sellerUid;
    private String orderStatus;
    private String id;
    private String aid;
    private String oid;
    private String addtime;
    private String status;
    private String cmoney;
    private String pmoney;
    private String needpay;
    private String gmoney;
    private AddressBean address;
    private String shopname;
    private String fee;
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

    public String getCmoney() {
        return cmoney;
    }

    public void setCmoney(String cmoney) {
        this.cmoney = cmoney;
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

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class AddressBean {
        /**
         * username :
         * phone :
         * address : 11111
         * area :
         */

        private String username;
        private String phone;
        private String address;
        private String area;

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
    }

    public static class GoodsBean {
        /**
         * price : 12
         * id : 2
         * img : Public/Uploads/Order/2019-03-26//5c99eb1fc12a5.jpg
         * name : 2号商品
         * num : 3
         */

        private String price;
        private String id;
        private String img;
        private String name;
        private String num;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
