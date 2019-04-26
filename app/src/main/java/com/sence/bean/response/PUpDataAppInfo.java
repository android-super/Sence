package com.sence.bean.response;

public class PUpDataAppInfo {
    private String version;
    private String upgrade_type;
    private String contents;
    private String add_time;
    private String link;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUpgrade_type() {
        return upgrade_type;
    }

    public void setUpgrade_type(String upgrade_type) {
        this.upgrade_type = upgrade_type;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}