package com.sence.bean.response;

import java.util.List;

public class PMyOrderBean {

    /**
     * allNum : 2
     * waitPay : 2
     * waitSend : 0
     * waitConfirm : 0
     * waitEvlua : 0
     * list : [{"sellerUid":"1","status":"2","statusMsg":"等待支付","id":"2","addtime":"2019-03-18 17:28:46","needpay":"8","num":"4","goods":[{"gid":"1","price":"4","num":"2","name":"一号商品","img":""}],"custom":{"nick_name":"hhhh","avatar":"","id":"1","name":"hhhh"}},{"id":"1","addtime":"2019-03-18 17:28:46","needpay":"12","goods":{"gid":"2","price":"4","num":"3","name":"2号商品","img":""},"custom":{"nick_name":"hhhh","avatar":"","id":"1","name":"hhhh"}}]
     */

    private String allNum;
    private String waitPay;
    private String waitSend;
    private String waitConfirm;
    private String waitEvlua;
    private List<ListBean> list;

    public String getAllNum() {
        return allNum;
    }

    public void setAllNum(String allNum) {
        this.allNum = allNum;
    }

    public String getWaitPay() {
        return waitPay;
    }

    public void setWaitPay(String waitPay) {
        this.waitPay = waitPay;
    }

    public String getWaitSend() {
        return waitSend;
    }

    public void setWaitSend(String waitSend) {
        this.waitSend = waitSend;
    }

    public String getWaitConfirm() {
        return waitConfirm;
    }

    public void setWaitConfirm(String waitConfirm) {
        this.waitConfirm = waitConfirm;
    }

    public String getWaitEvlua() {
        return waitEvlua;
    }

    public void setWaitEvlua(String waitEvlua) {
        this.waitEvlua = waitEvlua;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * sellerUid : 1
         * status : 2
         * statusMsg : 等待支付
         * id : 2
         * addtime : 2019-03-18 17:28:46
         * needpay : 8
         * num : 4
         * goods : [{"gid":"1","price":"4","num":"2","name":"一号商品","img":""}]
         * custom : {"nick_name":"hhhh","avatar":"","id":"1","name":"hhhh"}
         */

        private String sellerUid;
        private String status;
        private String statusMsg;
        private String id;
        private String addtime;
        private String needpay;
        private String num;
        private CustomBean custom;
        private List<GoodsBean> goods;

        public String getSellerUid() {
            return sellerUid;
        }

        public void setSellerUid(String sellerUid) {
            this.sellerUid = sellerUid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusMsg() {
            return statusMsg;
        }

        public void setStatusMsg(String statusMsg) {
            this.statusMsg = statusMsg;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getNeedpay() {
            return needpay;
        }

        public void setNeedpay(String needpay) {
            this.needpay = needpay;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
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

        public static class CustomBean {
            /**
             * nick_name : hhhh
             * avatar :
             * id : 1
             * name : hhhh
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
             * gid : 1
             * price : 4
             * num : 2
             * name : 一号商品
             * img :
             */

            private String gid;
            private String price;
            private String num;
            private String name;
            private String img;

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

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
        }
    }
}
