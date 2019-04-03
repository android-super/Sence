package com.sence.base;

import android.os.Bundle;

/**
 * Created by zwy on 2019/4/3.
 * package_name is com.sence.base
 * 描述:SenceGit
 */
public interface BaseInterface {

    void initView();

    void initData();

    int onActLayout();

    void onActCreate(Bundle savedInstanceState);
}
