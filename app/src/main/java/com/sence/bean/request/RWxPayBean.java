package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/14.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RWxPayBean extends BaseRequestBean {
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    private String uid;
    private String purpose;

    public RWxPayBean(String uid, String purpose, String total_fee, String oid) {
        this.uid = uid;
        this.purpose = purpose;
        this.total_fee = total_fee;
        this.oid = oid;
    }

    private String total_fee;
    private String oid;
}
