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
        WZXQ,//文章详情
        SPXQ,//商品详情
        GRZL,//个人资料
        YHXX,//用户协议
        XTTZ,//系统通知
        HYXY,//会员协议
    }

    public static String buildWebUrl(String url, String uid) {
        return url + "?uid=" + uid;
    }

    public static String buildToken(String url, String key, String value) {
        return url + "?token=" + LoginStatus.getUserToken() + "&" + key + "=" + value;
    }

    public static final String YSZC = "https://sence.forhour.com/Public/web/deal/privacy.html";//隐私政策
    public static final String HY = "https://sence.forhour.com/Public/web/flower/index.html";//花园
    public static final String YHXX = "https://sence.forhour.com/Public/web/deal/user.html";//用户协议
    public static final String HYXY = "https://sence.forhour.com/Public/web/deal/member.html";//会员协议

    public static final String WZXQ = "http://www.maysence.com/share/details.html";//文章详情
    public static final String SPXQ = "http://www.maysence.com/share/goods.html";//商品详情
    public static final String GRZL = "http://www.maysence.com/share/datum.html";//个人资料
    
}
