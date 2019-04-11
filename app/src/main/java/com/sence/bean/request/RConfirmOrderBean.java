package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

import org.json.JSONArray;

public class RConfirmOrderBean extends BaseRequestBean {
    private JSONArray goods;
    private String uid;
    private String addrId;

    public RConfirmOrderBean(JSONArray goods, String uid, String addrId) {
        this.goods = goods;
        this.uid = uid;
        this.addrId = addrId;
    }

    public JSONArray getGoods() {
        return goods;
    }

    public void setGoods(JSONArray goods) {
        this.goods = goods;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }
}
