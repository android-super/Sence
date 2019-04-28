package com.sence.net.manager;

/**
 * Created by zwy on 2019/3/20.
 * package_name is com.sence.net
 * 描述:SenceGit
 */
public interface ApiCallBack<P> {
    void onFinish();

    void Message(int code, String message);

    void onSuccess(P p, String msg);
}
