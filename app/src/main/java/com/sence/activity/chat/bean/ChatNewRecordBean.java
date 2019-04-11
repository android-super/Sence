package com.sence.activity.chat.bean;

import java.util.List;

public class ChatNewRecordBean {

    /**
     * status : 1
     * msg : 请求成功
     * data : [{"id":"22","u_from":"2","u_to":"8595","content":"一二三","add_time":"1513234082","status":"2","home_id":"4","state":"1"},{"id":"21","u_from":"2","u_to":"8595","content":"哈哈","add_time":"1513234023","status":"2","home_id":"4","state":"1"},{"id":"20","u_from":"2","u_to":"8595","content":"哈哈","add_time":"1513233944","status":"2","home_id":"0","state":"0"},{"id":"19","u_from":"2","u_to":"8595","content":"你好","add_time":"1513233621","status":"2","home_id":"0","state":"0"},{"id":"15","u_from":"8595","u_to":"2","content":"可是什么呢","add_time":"1513233149","status":"2","home_id":"4","state":"1"},{"id":"12","u_from":"8595","u_to":"2","content":"哈哈","add_time":"1513232957","status":"2","home_id":"0","state":"0"}]
     */

    private int status;
    private String msg;
    /**
     * id : 22
     * u_from : 2
     * u_to : 8595
     * content : 一二三
     * add_time : 1513234082
     * status : 2
     * home_id : 4
     * state : 1
     */

    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String u_from;
        private String u_to;
        private String content;
        private long add_time;
        private String status;
        private String home_id;
        private String state;
        private String img;
        private int type;
        private String tid;
        private boolean isTimeVisible = false;//计算每条消息是否显示时间


        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public boolean isTimeVisible() {
            return isTimeVisible;
        }

        public void setTimeVisible(boolean timeVisible) {
            isTimeVisible = timeVisible;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getU_from() {
            return u_from;
        }

        public void setU_from(String u_from) {
            this.u_from = u_from;
        }

        public String getU_to() {
            return u_to;
        }

        public void setU_to(String u_to) {
            this.u_to = u_to;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getHome_id() {
            return home_id;
        }

        public void setHome_id(String home_id) {
            this.home_id = home_id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}