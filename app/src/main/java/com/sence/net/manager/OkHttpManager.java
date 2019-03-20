package com.sence.net.manager;

import com.sence.base.BaseApp;
import com.sence.net.interceptor.LoggerInterceptor;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by zwy on 2019/3/19.
 * package_name is com.sence.net
 * 描述:SenceGit
 */
public enum OkHttpManager {
    INSTANCE;

    private static Cache cache = new Cache(BaseApp.INSTANCE.getCacheDir(),1024*1024*10);//缓存10M
    public OkHttpClient create() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor())
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }
}
