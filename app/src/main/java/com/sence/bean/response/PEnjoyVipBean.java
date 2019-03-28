package com.sence.bean.response;

import java.util.List;

public class PEnjoyVipBean {

    /**
     * money : 2365
     * service : [{"id":"1","img":"","num":"33","tag":["完好旅游","无知的"],"star":"2"},{"id":"2","img":"","num":"40","tag":["完好旅放丁放的游","无知33的"],"star":"1"},{"id":"3","img":"","num":"60","tag":["哈哈哈","分散飞飞"],"star":"5"}]
     */

    private String money;
    private List<ServiceBean> service;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<ServiceBean> getService() {
        return service;
    }

    public void setService(List<ServiceBean> service) {
        this.service = service;
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
