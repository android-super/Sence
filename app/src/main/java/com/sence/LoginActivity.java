package com.sence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.blankj.utilcode.util.PhoneUtils;
import com.sence.activity.WebActivity;
import com.sence.bean.request.RLoginBean;
import com.sence.bean.response.PUserBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.StatusBarUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

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

        login_cancel.setOnClickListener(this);
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
                break;
        }
    }


    public void Login(String wechat_openid) {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_LOGIN, new RLoginBean(wechat_openid, "android",
                PhoneUtils.getIMEI())).request(new ApiCallBack<PUserBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PUserBean o, String msg) {
                SharedPreferencesUtil.getInstance().putBoolean("is_login", true);
                SharedPreferencesUtil.getInstance().putString("uid", o.getId());
                SharedPreferencesUtil.getInstance().putString("user_name", o.getUser_name());
                SharedPreferencesUtil.getInstance().putString("nick_name", o.getNick_name());
                SharedPreferencesUtil.getInstance().putString("sex", o.getSex());
                SharedPreferencesUtil.getInstance().putString("avatar", o.getAvatar());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
            if (UMShareAPI.get(LoginActivity.this).isAuthorize(LoginActivity.this, SHARE_MEDIA.WEIXIN)) {
                String open_id = map.get("openid");
                Login(open_id);
            } else {
                Intent intent = new Intent(LoginActivity.this, BindPhoneActivity.class);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    intent.putExtra(key, value);
                }
                startActivity(intent);
            }


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
