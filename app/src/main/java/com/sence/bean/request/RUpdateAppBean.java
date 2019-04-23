package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/22.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RUpdateAppBean extends BaseRequestBean {
    public RUpdateAppBean(String device_type, String version) {
        this.device_type = device_type;
        this.version = version;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private String device_type;
    private String version;
}
