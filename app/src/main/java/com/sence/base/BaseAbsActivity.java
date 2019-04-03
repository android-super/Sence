package com.sence.base;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseAbsActivity extends AppCompatActivity implements BaseInterface {

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onBack(boolean isBack) {
        if (isBack) {
            this.finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void onBack(View.OnClickListener onClickListener) {
        if (onClickListener == null) {
            return;
        }
    }

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
    }
}
