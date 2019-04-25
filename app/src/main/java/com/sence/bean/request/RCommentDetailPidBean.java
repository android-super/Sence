package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/12.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RCommentDetailPidBean extends BaseRequestBean {
    public RCommentDetailPidBean(String uid, String rid, String p_uid, String content, String type, String pid) {
        this.uid = uid;
        this.rid = rid;
        this.p_uid = p_uid;
        this.content = content;
        this.type = type;
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getP_uid() {
        return p_uid;
    }

    public void setP_uid(String p_uid) {
        this.p_uid = p_uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String uid;
    private String rid;
    private String p_uid;
    private String content;
    private String type;
    private String pid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
