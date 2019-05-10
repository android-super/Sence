package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/3/28.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RLoginBean extends BaseRequestBean {
    private String wechat_openid;
    private String device_type;
    private String device;
    private String wechat_unionid;
    public RLoginBean(String wechat_openid, String device_type, String device,String wechat_unionid) {
        this.wechat_openid = wechat_openid;
        this.device_type = device_type;
        this.device = device;
        this.wechat_unionid = wechat_unionid;
    }

    public String getWechat_unionid() {
        return wechat_unionid;
    }

    public void setWechat_unionid(String wechat_unionid) {
        this.wechat_unionid = wechat_unionid;
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
}
