package com.sence.bean.request;

import com.sence.bean.base.BaseFileRequestBean;
import com.sence.bean.base.BaseImageRequestBean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zwy on 2019/4/10.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RSendImgFileBean extends BaseFileRequestBean {
    private File img;

    public RSendImgFileBean(File img) {
        this.img = img;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

    @Override
    public Map getImgMap() {
        Map<String, File> map = new HashMap<>();
        map.put("img", getImg());
        return map;
    }
}
