package com.sence.bean.base;

/**
 * Created by zwy on 2019/3/19.
 * package_name is com.sence.bean
 * 描述:SenceGit
 */
public class BaseResponseBean<P> {
    private int status;
    private int count;
    private String msg;
    private P data;

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

    public P getData() {
        return data;
    }

    public void setData(P data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
