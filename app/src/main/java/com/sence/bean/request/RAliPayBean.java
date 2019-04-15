package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/14.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RAliPayBean extends BaseRequestBean {
    public RAliPayBean(String purpose, String uid, String total_amount, String exp) {
        this.purpose = purpose;
        this.uid = uid;
        this.total_amount = total_amount;
        this.exp = exp;
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

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    private String purpose;
    private String uid;
    private String total_amount;
    private String exp;
}
