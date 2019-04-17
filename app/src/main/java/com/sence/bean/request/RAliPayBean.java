package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/14.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RAliPayBean extends BaseRequestBean {
    public RAliPayBean(String purpose, String uid, String total_amount, String oid) {
        this.purpose = purpose;
        this.uid = uid;
        this.total_amount = total_amount;
        this.oid = oid;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    private String purpose;
    private String uid;
    private String total_amount;
    private String oid;
}
