package com.sence.bean.response;

import java.util.List;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.bean.response
 * 描述:SenceGit
 */
public class PServeBean {

    /**
     * id : 113
     * title : 1
     * content : 1
     * img :
     */

    private String id;
    private String title;
    private String content;
    private String img;
    /**
     * star : 2
     * tag : ["镶钻"]
     * num : 10
     */

    private String star;
    private String num;
    private List<String> tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
}
