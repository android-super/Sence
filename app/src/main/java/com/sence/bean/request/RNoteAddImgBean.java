package com.sence.bean.request;

import com.sence.bean.base.BaseImageRequestBean;

/**
 * Created by zwy on 2019/4/13.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RNoteAddImgBean extends BaseImageRequestBean {
    public RNoteAddImgBean(String uid, String type, String content, String width, String height, String tag_info) {
        this.uid = uid;
        this.type = type;
        this.content = content;
        this.width = width;
        this.height = height;
        this.tag_info = tag_info;
    }

    private String uid;
    private String type;
    private String content;
    private String width;
    private String height;

    public String getTag_info() {
        return tag_info;
    }

    public void setTag_info(String tag_info) {
        this.tag_info = tag_info;
    }

    private String tag_info;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
