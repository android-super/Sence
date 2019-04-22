package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

public class RReportBean extends BaseRequestBean {
    private String uid;
    private String gid;
    private String type;
    private String content;
    private String to_uid;

    public RReportBean(String uid, String gid, String type, String content, String to_uid) {
        this.uid = uid;
        this.gid = gid;
        this.type = type;
        this.content = content;
        this.to_uid = to_uid;
    }
}
