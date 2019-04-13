package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.DataCleanManager;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置页面
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.pb_title_setting)
    PubTitle pbTitleSetting;
    @BindView(R.id.ll_autonym_setting)
    LinearLayout llAutonymSetting;
    @BindView(R.id.ll_clear_setting)
    LinearLayout llClearSetting;
    @BindView(R.id.ll_policy_setting)
    LinearLayout llPolicySetting;
    @BindView(R.id.ll_about_setting)
    LinearLayout llAboutSetting;
    @BindView(R.id.tv_quit_setting)
    TextView tvQuitSetting;
    @BindView(R.id.tv_cache_setting)
    TextView tvCacheSetting;

    @Override
    public int onActLayout() {
        return R.layout.activity_setting;
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
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
            tvCacheSetting.setText(totalCacheSize);
        } catch (Exception e) {

        }
    }

    @OnClick({R.id.ll_autonym_setting, R.id.ll_clear_setting, R.id.ll_policy_setting, R.id.ll_about_setting, R.id.tv_quit_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_autonym_setting:
                startActivity(new Intent(SettingActivity.this, AutonymActivity.class));
                break;
            case R.id.ll_clear_setting:
                DataCleanManager.clearAllCache(SettingActivity.this);
                try {
                    String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
                    tvCacheSetting.setText(totalCacheSize);
                } catch (Exception e) {

                }
                break;
            case R.id.ll_policy_setting:
                startActivity(new Intent(SettingActivity.this, WebActivity.class));
                break;
            case R.id.ll_about_setting:
                startActivity(new Intent(SettingActivity.this, AboutActivity.class));
                break;
            case R.id.tv_quit_setting:
                SharedPreferencesUtil.getInstance().putBoolean("is_login", false);
                break;
        }
    }
}
