package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RCashBean extends BaseRequestBean {
    public RCashBean(String uid, String money, String bank_id) {
        this.uid = uid;
        this.money = money;
        this.bank_id = bank_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    private String uid;
    private String money;
    private String bank_id;
}
