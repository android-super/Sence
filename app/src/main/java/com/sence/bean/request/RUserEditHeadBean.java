package com.sence.bean.request;

import com.sence.bean.base.BaseFileRequestBean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zwy on 2019/4/11.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RUserEditHeadBean extends BaseFileRequestBean {

    public RUserEditHeadBean(File avatar) {
        this.avatar = avatar;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    private File avatar;
    @Override
    public Map getImgMap() {
        Map<String, File> map = new HashMap<>();
        map.put("avatar",getAvatar());
        return map;
    }
}
