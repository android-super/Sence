package com.sence.bean.response;

/**
 * Created by zwy on 2019/4/17.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PChatPrivateMessageBean {

    /**
     * id : 643
     * u_from : 3
     * u_to : 4
     * content : 5ZOI5ZOI5ZOI
     * width : 0.00
     * height : 0.00
     * type : 1
     * status : 0
     * state : 1
     * add_time : 2019-04-17 11:39:59
     * img :
     */

    private String id;
    private String u_from;
    private String u_to;
    private String content;
    private String width;
    private String height;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    private int type;
    private String status;
    private String state;
    private long add_time;
    private String img;
    private boolean isTimeVisible = false;//计算每条消息是否显示时间

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

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isTimeVisible() {
        return isTimeVisible;
    }

    public void setTimeVisible(boolean timeVisible) {
        isTimeVisible = timeVisible;
    }
}
