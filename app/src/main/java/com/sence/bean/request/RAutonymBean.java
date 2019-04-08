package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RAutonymBean extends BaseRequestBean {
    private String type;
    private String real_name;
    private String certificates_number;
    private String uid;

    public RAutonymBean(String type, String real_name, String certificates_number, String uid) {
        this.type = type;
        this.real_name = real_name;
        this.certificates_number = certificates_number;
        this.uid = uid;
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

    public String getCertificates_number() {
        return certificates_number;
    }

    public void setCertificates_number(String certificates_number) {
        this.certificates_number = certificates_number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
