package com.sence.bean.response;

import java.util.List;

public class PMyInfoServiceBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 108
         * img : /Public/Uploads/Service/2019-03-28/5c9c4551b2ae1.jpg
         * star : 5
         * num : 7
         * tag : ["迪奥Dior","纪梵希GIVENCHY ","圣罗兰YSL"]
         */

        private String id;
        private String img;
        private String star;
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
