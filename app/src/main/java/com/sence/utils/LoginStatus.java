package com.sence.utils;

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
        return uid == null ? "1" : uid;
    }

    public static String getAvatar() {
        String avatar = SharedPreferencesUtil.getInstance().getString("avatar");
        return avatar;
    }

    public static String getName() {
        String name = SharedPreferencesUtil.getInstance().getString("nick_name");
        return name;
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
    public static boolean getIsCheckOrderAddress() {
        boolean iscleck = SharedPreferencesUtil.getInstance().getBoolean("ischeck_orderaddress");
        return iscleck;
    }
}
