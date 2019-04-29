package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RCashBean extends BaseRequestBean {

    public RCashBean(String uid, String money, String bank_id, String code_id, String user_name, String code_num) {
        this.uid = uid;
        this.money = money;
        this.bank_id = bank_id;
        this.code_id = code_id;
        this.user_name = user_name;
        this.code_num = code_num;
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
    private String code_id;
    private String user_name;
    private String code_num;

    public String getCode_id() {
        return code_id;
    }

    public void setCode_id(String code_id) {
        this.code_id = code_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCode_num() {
        return code_num;
    }

    public void setCode_num(String code_num) {
        this.code_num = code_num;
    }
}
