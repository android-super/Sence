package com.sence.net.manager;

/**
 * Created by zwy on 2019/4/22.
 * package_name is com.sence.net.manager
 * 描述:SenceGit
 */
public abstract class MessageApiCallBack<P> implements ApiCallBack {
    public abstract void onSuccessCount(P p, String msg, int count);
}
