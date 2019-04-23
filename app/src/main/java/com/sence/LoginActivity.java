package com.sence;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.sence.activity.WebActivity;
import com.sence.activity.web.WebConstans;
import com.sence.base.BaseActivity;
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

import butterknife.BindView;

/**
 * 登录页
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.login_cancel)
    TextView loginCancel;
    @BindView(R.id.login_check)
    ImageView loginCheck;
    @BindView(R.id.login_agree)
    TextView loginAgree;
    @BindView(R.id.login_protocol)
    TextView loginProtocol;
    @BindView(R.id.login_rule)
    TextView loginRule;
    @BindView(R.id.login_look)
    TextView loginLook;
    @BindView(R.id.login_wx)
    LinearLayout loginWx;

    @Override
    public int onActLayout() {
        return R.layout.activity_login;
    }

    public void initView() {
        StatusBarUtil.setTranslucent(this, 0);
        StatusBarUtil.setDarkMode(this);

        loginCancel.setOnClickListener(this);
        loginCheck.setOnClickListener(this);
        loginWx.setOnClickListener(this);
        loginAgree.setOnClickListener(this);
        loginProtocol.setOnClickListener(this);
        loginLook.setOnClickListener(this);
        loginRule.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_check:
            case R.id.login_agree:
                if (loginCheck.isSelected()) {
                    loginCheck.setSelected(false);
                } else {
                    loginCheck.setSelected(true);
                }
                break;
            case R.id.login_cancel:
                finish();
                break;
            case R.id.login_protocol:
                Intent intent = new Intent(LoginActivity.this, WebActivity.class);
                intent.putExtra("code", WebConstans.WebCode.XKXY);
                startActivity(intent);
                break;
            case R.id.login_rule:
                intent = new Intent(LoginActivity.this, WebActivity.class);
                intent.putExtra("code", WebConstans.WebCode.YSZC);
                startActivity(intent);
                break;
            case R.id.login_look:
                finish();
                break;
            case R.id.login_wx:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
        }
    }


    public void Login(String wechat_openid, final Map<String, String> map) {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_LOGIN, new RLoginBean(wechat_openid, "android",
                PhoneUtils.getIMEI())).request(new ApiCallBack<PUserBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
                if (code == 3) {//登录失败，未绑定手机号
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
            public void onSuccess(PUserBean o, String msg) {
                SharedPreferencesUtil.getInstance().putBoolean("is_login", true);
                SharedPreferencesUtil.getInstance().putString("uid", o.getId());
                SharedPreferencesUtil.getInstance().putString("user_name", o.getUser_name());
                SharedPreferencesUtil.getInstance().putString("nick_name", o.getNick_name());
                SharedPreferencesUtil.getInstance().putString("sex", o.getSex());
                SharedPreferencesUtil.getInstance().putString("avatar", o.getAvatar());
                SharedPreferencesUtil.getInstance().putString("real_name", o.getReal_name());
                SharedPreferencesUtil.getInstance().putString("id_card", o.getNick_name());
                SharedPreferencesUtil.getInstance().putString("id_status", o.getId_status());
                SharedPreferencesUtil.getInstance().putString("img_status", o.getImg_status());
                SharedPreferencesUtil.getInstance().putString("token", o.getToken());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
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
                Login(open_id, map);
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
