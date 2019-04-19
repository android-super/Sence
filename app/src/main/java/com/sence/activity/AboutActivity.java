package com.sence.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 关于
 */

public class AboutActivity extends BaseActivity {

    @BindView(R.id.tv_version_about)
    TextView tvVersionAbout;

    @Override
    public int onActLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
