package com.sence.net.bean;

public class HttpJsonErrorBean {
    private String msg;

    private int status;

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}