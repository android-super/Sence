package com.sence.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.sence.R;
import com.sence.activity.web.WebConstans;
import com.sence.base.BaseActivity;
import com.sence.utils.DataCleanManager;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

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
    @BindView(R.id.ll_binding_setting)
    LinearLayout llBindingSetting;
    @BindView(R.id.ll_blacklist_setting)
    LinearLayout llBlacklistSetting;

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

    @OnClick({R.id.ll_binding_setting,R.id.ll_blacklist_setting,R.id.ll_autonym_setting, R.id.ll_clear_setting, R.id.ll_policy_setting, R.id.ll_about_setting, R.id.tv_quit_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_autonym_setting:
                startActivity(new Intent(SettingActivity.this, AutonymActivity.class));
                break;
            case R.id.ll_clear_setting:
                View viewClear = View.inflate(SettingActivity.this, R.layout.alter_deleteorder, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this, R.style.AlertDialogStyle);
                builder.setView(viewClear);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(true);
                alertDialog.show();
                alertDialog.getWindow().setLayout(new DensityUtil().dip2px(270), LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView mContent = viewClear.findViewById(R.id.tv_content_deleteorder);
                mContent.setText("您确认要清除缓存吗");
                TextView mCancel = viewClear.findViewById(R.id.tv_cancel_deleteorder);
                TextView mConfirm = viewClear.findViewById(R.id.tv_confirm_deleteorder);
                mCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                mConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        DataCleanManager.clearAllCache(SettingActivity.this);
                        try {
                            String totalCacheSize = DataCleanManager.getTotalCacheSize(SettingActivity.this);
                            tvCacheSetting.setText(totalCacheSize);
                        } catch (Exception e) {

                        }
                    }
                });

                break;
            case R.id.ll_binding_setting:
                startActivity(new Intent(SettingActivity.this, BindingLinkManActivity.class));
                break;
            case R.id.ll_blacklist_setting:
                startActivity(new Intent(SettingActivity.this, BlackListActivity.class));
                break;
            case R.id.ll_policy_setting:
                Intent intent = new Intent(SettingActivity.this, WebActivity.class);
                intent.putExtra("code", WebConstans.WebCode.YSZC);
                startActivity(intent);
                break;
            case R.id.ll_about_setting:
                startActivity(new Intent(SettingActivity.this, AboutActivity.class));
                break;
            case R.id.tv_quit_setting:
                View viewQuit = View.inflate(SettingActivity.this, R.layout.alter_deleteorder, null);
                AlertDialog.Builder builderviewQuit = new AlertDialog.Builder(SettingActivity.this, R.style.AlertDialogStyle);
                builderviewQuit.setView(viewQuit);
                final AlertDialog dialog = builderviewQuit.create();
                dialog.setCancelable(true);
                dialog.show();
                dialog.getWindow().setLayout(new DensityUtil().dip2px(270), LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView mContentQuit = viewQuit.findViewById(R.id.tv_content_deleteorder);
                mContentQuit.setText("您确认要退出登录吗");
                TextView mCancelQuit = viewQuit.findViewById(R.id.tv_cancel_deleteorder);
                TextView mConfirmQuit = viewQuit.findViewById(R.id.tv_confirm_deleteorder);
                mCancelQuit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                mConfirmQuit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        SharedPreferencesUtil.getInstance().putBoolean("is_login", false);
                        SharedPreferencesUtil.getInstance().putString("uid", "");
                        UMShareAPI.get(SettingActivity.this).deleteOauth(SettingActivity.this, SHARE_MEDIA.WEIXIN,
                                umAuthListener);
                        finish();
                    }
                });
                break;
        }
    }

    /**
     * 第三方登录回调监听
     */
    public UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
////                String key = entry.getKey();
////                String value = entry.getValue();
////            }
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

}
