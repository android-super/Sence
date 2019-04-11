package com.sence.bean.request;

import java.util.List;

public class RConfirmOrderGoodBean {
    private List<Good> goods;

    public RConfirmOrderGoodBean(List<Good> goods) {
        this.goods = goods;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }
    public static class Good{
        private String id;
        private String num;

        public Good(String id, String num) {
            this.id = id;
            this.num = num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
