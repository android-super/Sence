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
}
