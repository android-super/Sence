package com.sence;

import android.content.Entity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sence.activity.WebActivity;
import com.sence.utils.StatusBarUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

/**
 * 登录页
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView login_cancel;
    private ImageView login_check;
    private TextView login_agree;
    private TextView login_protocol;
    private TextView login_rule;
    private TextView login_look;
    private LinearLayout login_wx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTranslucent(this, 0);
        StatusBarUtil.setDarkMode(this);
        initView();
    }

    private void initView() {
        login_cancel = findViewById(R.id.login_cancel);
        login_check = findViewById(R.id.login_check);
        login_agree = findViewById(R.id.login_agree);
        login_protocol = findViewById(R.id.login_protocol);
        login_rule = findViewById(R.id.login_rule);
        login_look = findViewById(R.id.login_look);
        login_wx = findViewById(R.id.login_wx);

        login_check.setOnClickListener(this);
        login_wx.setOnClickListener(this);
        login_agree.setOnClickListener(this);
        login_protocol.setOnClickListener(this);
        login_look.setOnClickListener(this);
        login_rule.setOnClickListener(this);
        login_wx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_check:
            case R.id.login_agree:
                if (login_check.isSelected()) {
                    login_check.setSelected(false);
                } else {
                    login_check.setSelected(true);
                }
                break;
            case R.id.login_cancel:
                finish();
                break;
            case R.id.login_protocol:
                startActivity(new Intent(LoginActivity.this, WebActivity.class));
                break;
            case R.id.login_rule:
                startActivity(new Intent(LoginActivity.this, WebActivity.class));
                break;
            case R.id.login_look:
                finish();
                break;
            case R.id.login_wx:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                startActivity(new Intent(LoginActivity.this, BindPhoneActivity.class));
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
            Set<String> keySet = map.keySet();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
