package com.sence.activity.chat.bean;

public class ChatSocketBean {

    private String type;
    private String content;
    private String u_from;
    private String client_id;
    private String group;
    private String msg;
    private String img;
    private long add_time;
    private int message_type;
    private String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getU_from() {
        return u_from;
    }

    public void setU_from(String u_from) {
        this.u_from = u_from;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
