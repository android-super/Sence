package com.sence.bean.response;

/**
 * Created by zwy on 2019/3/29.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class PAccountBean {

    /**
     * uid : 4
     * money : 66.66
     * partnerIncome : 0
     */

    private String uid;
    private String money;
    private String partnerIncome;

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

    public String getPartnerIncome() {
        return partnerIncome;
    }

    public void setPartnerIncome(String partnerIncome) {
        this.partnerIncome = partnerIncome;
    }
}
