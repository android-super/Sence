package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/20.
 * package_name is com.sence.bean
 * 描述:SenceGit
 */
public class RRegisterBean extends BaseRequestBean {
    private String user_name;


    public RRegisterBean(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
