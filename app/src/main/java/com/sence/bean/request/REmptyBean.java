package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/29.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class REmptyBean extends BaseRequestBean {
    public REmptyBean() {
        this.empty = "0";
    }

    private String empty;

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }
}
