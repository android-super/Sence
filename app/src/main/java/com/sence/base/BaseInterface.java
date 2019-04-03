package com.sence.base;

import android.os.Bundle;
import android.view.View;

/**
 * Created by zwy on 2019/4/3.
 * package_name is com.sence.base
 * 描述:SenceGit
 */
public interface BaseInterface {
    void onBack(boolean isBack);

    void onBack(View.OnClickListener onClickListener);

    void initView();

    void initData();

    int onActLayout();

    void onActCreate(Bundle savedInstanceState);
}
