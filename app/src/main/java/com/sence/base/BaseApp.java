package com.sence.base;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sence.net.manager.HttpClientManager;
import com.sence.utils.SharedPreferencesUtil;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import androidx.multidex.MultiDex;

/**
 * Created by zwy on 2019/3/19.
 * package_name is com.sence.base
 * 描述:SenceGit
 */
public class BaseApp extends Application {
    public static BaseApp INSTANCE;
    private static BaseApp mBaseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        MultiDex.install(this);
        initLeakCanary();
        initLogger();
        initUtil();
        initHttpManager();
        initUM();
        initSharedPreference();
    }
    public static BaseApp getContext() {
        return mBaseApp;
    }
    /**
     * 数据保存
     */
    private void initSharedPreference() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    /**
     * Umeng分享，第三方登录
     */
    private void initUM() {
        UMConfigure.init(this, "5c99859a203657099a00116c", "umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "");
        PlatformConfig.setWeixin("wx4045a7f46e598ff1", "17e8a40b58971544024dd0a1328b3a55");
    }

    /**
     * 网络初始化
     */
    private void initHttpManager() {
        HttpClientManager.Instance.init();
    }

    /**
     * 工具类
     */
    private void initUtil() {
        Utils.init(this);
    }

    /**
     * 崩溃泄漏日志
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * 日志打印
     */
    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
