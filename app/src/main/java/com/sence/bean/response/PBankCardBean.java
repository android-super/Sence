package com.sence.bean.response;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PBankCardBean {

    /**
     * id : 531
     * card_num : 123321123312123321
     * bank_id : 1
     * branch_name : 啊实打实大所
     * bank_name : 中国工商银行
     * bank_img : /Public/Uploads/notice/2017-09-05/15045997644923122.png
     * bank_img_two : /Public/Uploads/notice/2017-09-05/15045997644929348.png
     */

    private String id;
    private String card_num;
    private String bank_id;
    private String branch_name;
    private String bank_name;
    private String bank_img;
    private String bank_img_two;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_img() {
        return bank_img;
    }

    public void setBank_img(String bank_img) {
        this.bank_img = bank_img;
    }

    public String getBank_img_two() {
        return bank_img_two;
    }

    public void setBank_img_two(String bank_img_two) {
        this.bank_img_two = bank_img_two;
    }
}
