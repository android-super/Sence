package com.sence.bean.response;

import java.util.List;

/**
 * Created by zwy on 2019/3/27.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PUserVipBean {


    /**
     * isMember : 0
     * carousel : 大猫吃小鱼 刚刚加入了Sence会员
     * money : 2365
     * goods : [{"id":"8","name":"一号商品","img":"Public/Uploads/Order/2019-03-26//5c99eb1fc12a5.jpg","price":"5",
     * "saveMoney":"5"},{"id":"3","name":"12aaa01","img":"/Public/Uploads/Goods/2019-03-27/5c9ad66aa3d18.jpg",
     * "price":"4","saveMoney":"11"},{"id":"2","name":"2号商品","img":"Public/Uploads/Order/2019-03-26//5c99eb1fc12a5
     * .jpg","price":"5","saveMoney":"10"},{"id":"1","name":"虹越园艺家 欧洲藤本月季花苗木 龙沙宝石1.5年嫁接盆栽苗 包邮",
     * "img":"/Public/Uploads/Goods/2019-03-27/5c9afaf63f8b8.jpg","price":"40","saveMoney":"10"}]
     * service : [{"id":"1","img":"","num":"33","tag":["完好旅游","无知的"],"star":"2"}]
     */

    private String isMember;
    private String carousel;
    private String money;
    private List<GoodsBean> goods;
    private List<ServiceBean> service;

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public String getCarousel() {
        return carousel;
    }

    public void setCarousel(String carousel) {
        this.carousel = carousel;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public List<ServiceBean> getService() {
        return service;
    }

    public void setService(List<ServiceBean> service) {
        this.service = service;
    }

    public static class GoodsBean {
        /**
         * id : 8
         * name : 一号商品
         * img : Public/Uploads/Order/2019-03-26//5c99eb1fc12a5.jpg
         * price : 5
         * saveMoney : 5
         */

        private String id;
        private String name;
        private String img;
        private String price;
        private String saveMoney;

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

        public String getSaveMoney() {
            return saveMoney;
        }

        public void setSaveMoney(String saveMoney) {
            this.saveMoney = saveMoney;
        }
    }

    public static class ServiceBean {
        /**
         * id : 1
         * img :
         * num : 33
         * tag : ["完好旅游","无知的"]
         * star : 2
         */

        private String id;
        private String img;
        private String num;
        private String star;
        private List<String> tag;

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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }
    }
}
