package com.sence.net.manager;

import com.sence.net.HttpService;
import com.sence.net.Urls;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by zwy on 2019/3/19.
 * package_name is com.sence.net
 * 描述:SenceGit
 */
public enum HttpClientManager {
    Instance;
    public HttpService httpService;

    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.base_url)
                .client(OkHttpManager.INSTANCE.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        httpService = retrofit.create(HttpService.class);
    }
}
