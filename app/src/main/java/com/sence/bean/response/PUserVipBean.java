package com.sence.bean.response;

import java.util.List;

/**
 * Created by zwy on 2019/3/27.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PUserVipBean {

    /**
     * money : 6891
     * goods : [{"id":"27","name":"臭臭的香水打发士大夫的绯闻多多沟通的地方地方的","img":"/Public/Uploads/Goods/2019-04-23/5cbe8d9f8ec6c
     * .png","price":"560","subhead":"","saveMoney":"39"},{"id":"26","name":"美美的女装的说法多发点发大水发射点法大师傅打发士",
     * "img":"/Public/Uploads/Goods/2019-04-23/5cbe8dbdf3ba0.png","price":"833","subhead":"","saveMoney":"67"},{"id
     * ":"25","name":"好好学习的健身器基于哦黄金矿工花椒粉胡椒粉和南非和甲方","img":"/Public/Uploads/Goods/2019-04-23/5cbe8c9063292.jpg",
     * "price":"13000","subhead":"","saveMoney":"1000"},{"id":"24","name":"天天向上的红酒 215g 刷了卡给快递的结果打开两个",
     * "img":"/Public/Uploads/Goods/2019-04-23/5cbe8c053be23.png","price":"7033","subhead":"0","saveMoney":"-5953"},{
     * "id":"23","name":"2019最新款耳机机构行家有同居体育等法规和小伙伴幸福感","img":"/Public/Uploads/Goods/2019-04-23/5cbe8b5ddb8db.png",
     * "price":"630","subhead":"","saveMoney":"20"}]
     * service : [{"id":"115","img":"/Public/Uploads/Service/2019-04-24/5cc0357143600.png","star":"0","tag":["快捷洗车",
     * "高品质保养"],"content":"好好学习 天天向上","title":"国贸洗车","num":"12"}]
     * isMember : 0
     * carousel : ["Joe 刚刚加入了Sence会员","lzy 刚刚加入了Sence会员","相濡以沫ふ 刚刚加入了Sence会员","请多指教 刚刚加入了Sence会员","o～糖菓菓o_O
     * 刚刚加入了Sence会员"]
     */

    private String money;
    private String isMember;
    private List<GoodsBean> goods;
    private List<ServiceBean> service;
    private List<String> carousel;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
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

    public List<String> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<String> carousel) {
        this.carousel = carousel;
    }

    public static class GoodsBean {
        /**
         * id : 27
         * name : 臭臭的香水打发士大夫的绯闻多多沟通的地方地方的
         * img : /Public/Uploads/Goods/2019-04-23/5cbe8d9f8ec6c.png
         * price : 560
         * subhead :
         * saveMoney : 39
         */

        private String id;
        private String name;
        private String img;
        private String price;
        private String subhead;
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

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
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
         * id : 115
         * img : /Public/Uploads/Service/2019-04-24/5cc0357143600.png
         * star : 0
         * tag : ["快捷洗车","高品质保养"]
         * content : 好好学习 天天向上
         * title : 国贸洗车
         * num : 12
         */

        private String id;
        private String img;
        private String star;
        private String content;
        private String title;
        private String num;
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

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }
    }
}
