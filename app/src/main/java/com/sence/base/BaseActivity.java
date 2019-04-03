package com.sence.base;

import android.os.Bundle;
import androidx.annotation.Nullable;

public abstract class BaseActivity extends BaseAbsActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onActCreate(savedInstanceState);
        setContentView(onActLayout());
        initView();
    }
}
