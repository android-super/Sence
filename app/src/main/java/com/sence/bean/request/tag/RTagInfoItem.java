package com.sence.bean.request.tag;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by zwy on 2019/4/15.
 * package_name is com.sence.bean.request.tag
 * 描述:SenceGit
 */
public class RTagInfoItem implements Serializable {
    public RTagInfoItem(double width_scale, double height_scale, String content) {
        this.width_scale = width_scale;
        this.height_scale = height_scale;
        this.content = content;
    }

    public double getWidth_scale() {
        return width_scale;
    }

    public void setWidth_scale(double width_scale) {
        this.width_scale = width_scale;
    }

    public double getHeight_scale() {
        return height_scale;
    }

    public void setHeight_scale(double height_scale) {
        this.height_scale = height_scale;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (TextUtils.isEmpty(content)) {
            this.content = "";
        } else {
            this.content = content;
        }
    }

    public RTagInfoItem(double width_scale, double height_scale) {
        this.width_scale = width_scale;
        this.height_scale = height_scale;
    }

    private double width_scale;
    private double height_scale;
    private String content;
}
