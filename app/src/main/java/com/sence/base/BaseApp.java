package com.sence.base;

import android.app.Application;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by zwy on 2019/3/19.
 * package_name is com.sence.base
 * 描述:SenceGit
 */
public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLeakCanary();
        initLogger();
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }


}
