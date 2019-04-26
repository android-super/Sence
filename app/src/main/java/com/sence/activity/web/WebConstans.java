package com.sence.activity.web;

import com.sence.utils.LoginStatus;

/**
 * Created by zwy on 2019/4/23.
 * package_name is com.sence.activity.web
 * 描述:SenceGit
 */
public class WebConstans {
    public enum WebCode {
        YSZC,//隐私政策
        HY,//花园
        XKXY,//许可协议
        WZXQ,//文章详情
        SPXQ,//商品详情
        GRZL,//个人资料
        YHXX,//用户协议
    }

    public static String buildWebUrl(String url, String uid) {
        return url + "?uid=" + uid;
    }

    public static String buildToken(String url, String key, String value) {
        return url + "?token=" + LoginStatus.getUserToken() + "&" + key + "=" + value;
    }

    public static final String YSZC = "http://sence.forhour.com/Public/web/deal/privacy.html";
    public static final String HY = "http://sence.forhour.com/Public/web/flower/index.html";
    public static final String WZXQ = "http://www.maysence.com/share/details.html";
    public static final String SPXQ = "http://www.maysence.com/share/goods.html";
    public static final String GRZL = "http://www.maysence.com/share/datum.html";
    public static final String YHXX = "http://sence.forhour.com/Public/web/deal/user.html";//用户协议
}
