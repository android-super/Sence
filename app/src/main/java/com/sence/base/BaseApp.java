package com.sence.base;

import android.app.Application;
import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sence.net.manager.HttpClientManager;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by zwy on 2019/3/19.
 * package_name is com.sence.base
 * 描述:SenceGit
 */
public class BaseApp extends Application {
    public static BaseApp INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        initLeakCanary();
        initLogger();
        initUtil();
        initHttpManager();
    }

    private void initHttpManager() {
        HttpClientManager.Instance.init();
    }

    private void initUtil() {
        Utils.init(this);
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }


}
