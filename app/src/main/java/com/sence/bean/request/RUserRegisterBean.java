package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/26.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RUserRegisterBean extends BaseRequestBean {
    private String user_name;
    private String code_id;
    private String code_num;
    private String device;
    private String device_type;
    private String wechat_openid;
    private String avatar;
    private String nick_name;

    public RUserRegisterBean(String user_name, String code_id, String code_num, String device, String device_type,
                             String wechat_openid, String avatar, String nick_name, String sex, String wechat_unionid) {
        this.user_name = user_name;
        this.code_id = code_id;
        this.code_num = code_num;
        this.device = device;
        this.device_type = device_type;
        this.wechat_openid = wechat_openid;
        this.avatar = avatar;
        this.nick_name = nick_name;
        this.sex = sex;
        this.wechat_unionid = wechat_unionid;
    }

    private String sex;


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCode_id() {
        return code_id;
    }

    public void setCode_id(String code_id) {
        this.code_id = code_id;
    }

    public String getCode_num() {
        return code_num;
    }

    public void setCode_num(String code_num) {
        this.code_num = code_num;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getWechat_openid() {
        return wechat_openid;
    }

    public void setWechat_openid(String wechat_openid) {
        this.wechat_openid = wechat_openid;
    }

    public String getWechat_unionid() {
        return wechat_unionid;
    }

    public void setWechat_unionid(String wechat_unionid) {
        this.wechat_unionid = wechat_unionid;
    }

    private String wechat_unionid;
}
