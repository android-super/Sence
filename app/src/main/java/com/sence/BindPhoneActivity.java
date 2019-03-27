package com.sence;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.Logger;
import com.sence.utils.StatusBarUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * 绑定手机号
 */
public class BindPhoneActivity extends AppCompatActivity {
    private ImageView bind_close;
    private EditText bind_number;
    private TextView bind_get_code;

    private String unionid;
    private String openid;
    private String gender;
    private String profile_image_url;
    private String name;
    private String screen_name;
    private String iconurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        StatusBarUtil.setLightMode(this);

        unionid = this.getIntent().getStringExtra("unionid");
        openid = this.getIntent().getStringExtra("openid");
        gender = this.getIntent().getStringExtra("gender");
        profile_image_url = this.getIntent().getStringExtra("profile_image_url");
        name = this.getIntent().getStringExtra("name");
        screen_name = this.getIntent().getStringExtra("screen_name");
        iconurl = this.getIntent().getStringExtra("iconurl");

        initView();
    }

    private void initView() {
        bind_close = findViewById(R.id.bind_close);
        bind_number = findViewById(R.id.bind_number);
        bind_get_code = findViewById(R.id.bind_get_code);
        bind_get_code.setEnabled(false);
        bind_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI.get(BindPhoneActivity.this).deleteOauth(BindPhoneActivity.this, SHARE_MEDIA.WEIXIN,
                        umAuthListener);
            }
        });

        bind_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 11) {
                    bind_get_code.setEnabled(true);
                    bind_get_code.setSelected(true);
                } else {
                    bind_get_code.setEnabled(false);
                    bind_get_code.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bind_get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = bind_number.getText().toString().trim();
                if (RegexUtils.isMobileExact(phone)) {
                    Intent intent = new Intent(BindPhoneActivity.this, VerifyActivity.class);
                    intent.putExtra("phone", bind_number.getText().toString());
                    intent.putExtra("unionid",unionid);
                    intent.putExtra("openid",openid);
                    intent.putExtra("profile_image_url",profile_image_url);
                    intent.putExtra("gender",gender);
                    intent.putExtra("name",name);
                    intent.putExtra("screen_name",screen_name);
                    intent.putExtra("iconurl",iconurl);
                    startActivity(intent);
                } else {
                    ToastUtils.showShort("请输入正确的手机号");
                }

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
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Logger.e(key + "=====" + value);
            }
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
