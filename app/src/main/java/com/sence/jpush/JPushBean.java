package com.sence.jpush;

/**
 * Created by zwy on 2019/4/26.
 * package_name is com.sence.jpush
 * 描述:SenceGit
 */
public class JPushBean {
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String to;//推送key 类型判断用
    private String oid;//订单id
    private String title;//系统推送标题
    private String link;//系统推送了链接
    private String id;//服务id
    private String uid;//花园
}
