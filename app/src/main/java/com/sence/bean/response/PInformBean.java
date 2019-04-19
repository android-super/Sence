package com.sence.bean.response;

public class PInformBean  {
    private String id;
    private String add_time;
    private String title;
    private String content;
    private boolean isflex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
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

    public boolean isIsflex() {
        return isflex;
    }

    public void setIsflex(boolean isflex) {
        this.isflex = isflex;
    }

    public PInformBean(String id, String add_time, String title, String content, boolean isflex) {
        this.id = id;
        this.add_time = add_time;
        this.title = title;
        this.content = content;
        this.isflex = isflex;
    }
}
