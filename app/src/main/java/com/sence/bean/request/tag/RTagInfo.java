package com.sence.bean.request.tag;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zwy on 2019/4/15.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RTagInfo implements Serializable {
    public RTagInfo() {

    }

    private int height; //图片高

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public ArrayList<RTagInfoItem> getTagInfoItems() {
        return tagInfoItems;
    }

    public void setTagInfoItems(ArrayList<RTagInfoItem> tagInfoItems) {
        this.tagInfoItems = tagInfoItems;
    }

    private int width;//图片宽
    private String url;//图片地址

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private ArrayList<RTagInfoItem> tagInfoItems;//图片标签信息
}
