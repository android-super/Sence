package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/9.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RCardAddBean extends BaseRequestBean {
    private String type;
    private String real_name;
    private String card_num;
    private String bank_id;
    private String uid;
    private String pre_phone;

    public RCardAddBean(String type, String real_name, String card_num, String bank_id,
                        String uid, String pre_phone, String branch_name) {
        this.type = type;
        this.real_name = real_name;
        this.card_num = card_num;
        this.bank_id = bank_id;
        this.uid = uid;
        this.pre_phone = pre_phone;
        this.branch_name = branch_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPre_phone() {
        return pre_phone;
    }

    public void setPre_phone(String pre_phone) {
        this.pre_phone = pre_phone;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    private String branch_name;
}
