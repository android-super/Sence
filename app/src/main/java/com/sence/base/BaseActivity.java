package com.sence.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseInterface {
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onActCreate(savedInstanceState);
        setContentView(onActLayout());
        unbinder = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}
