package com.sence.utils;

import android.text.TextUtils;

/**
 * Created by zwy on 2019/3/26.
 * package_name is com.sence.utils
 * 描述:SenceGit
 */
public class LoginStatus {
    public static boolean isLogin() {
        boolean is_login = SharedPreferencesUtil.getInstance().getBoolean("is_login");
        String uid = SharedPreferencesUtil.getInstance().getString("uid");
        return uid != null && is_login;
    }

    public static String getUid() {
        String uid = SharedPreferencesUtil.getInstance().getString("uid");
        return uid == null ? "" : uid;
    }

    public static boolean isVip() {
        String is_vip = SharedPreferencesUtil.getInstance().getString("is_vip");
        if (!TextUtils.isEmpty(is_vip) && is_vip.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    public static String getAvatar() {
        String avatar = SharedPreferencesUtil.getInstance().getString("avatar");
        return avatar;
    }

    public static String getUpuid() {
        String name = SharedPreferencesUtil.getInstance().getString("upuid");
        return name;
    }
    public static String getHistroy() {
        String histroy = SharedPreferencesUtil.getInstance().getString("histroy");
        return histroy;
    }
    public static String getInviteUserName() {
        String name = SharedPreferencesUtil.getInstance().getString("inviteUsername");
        return name;
    }

    public static String getName() {
        String name = SharedPreferencesUtil.getInstance().getString("nick_name");
        return name;
    }

    public static String getPayType() {
        String type = SharedPreferencesUtil.getInstance().getString("paytype");
        return type;
    }

    public static String getIdStatus() {
        String status = SharedPreferencesUtil.getInstance().getString("id_status");
        return status;
    }

    public static String getRealName() {
        String name = SharedPreferencesUtil.getInstance().getString("real_name");
        return name;
    }

    public static String getIdentity() {
        String card = SharedPreferencesUtil.getInstance().getString("id_card");
        return card;
    }

    public static String getImgStatus() {
        String status = SharedPreferencesUtil.getInstance().getString("img_status");
        return status;
    }

    public static String getNameAddress() {
        String status = SharedPreferencesUtil.getInstance().getString("name_address");
        return status;
    }

    public static String getIdAddress() {
        String status = SharedPreferencesUtil.getInstance().getString("id_address");
        return status;
    }

    public static String getAddress() {
        String status = SharedPreferencesUtil.getInstance().getString("address");
        return status;
    }

    public static String getPhoneAddress() {
        String status = SharedPreferencesUtil.getInstance().getString("phone_address");
        return status;
    }

    public static boolean getIsCheckAddress() {
        boolean iscleck = SharedPreferencesUtil.getInstance().getBoolean("ischeck_address");
        return iscleck;
    }

    public static boolean getIsCheckShopAddress() {
        boolean iscleck = SharedPreferencesUtil.getInstance().getBoolean("ischeck_shopaddress");
        return iscleck;
    }
    public static boolean getIsNullShopAddress() {
        boolean iscleck = SharedPreferencesUtil.getInstance().getBoolean("isnull_shopaddress");
        return iscleck;
    }



    public static String getToken() {
        String token = SharedPreferencesUtil.getInstance().getString("token");
        return token;
    }
}
