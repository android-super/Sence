package com.sence;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sence.utils.StatusBarUtil;

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
        StatusBarUtil.setDarkMode(this);
        initView();
    }

    private void initView() {
        login_cancel = findViewById(R.id.login_cancel);
        login_check = findViewById(R.id.login_check);
        login_agree = findViewById(R.id.login_agree);
        login_protocol = findViewById(R.id.login_protocol);
        login_rule = findViewById(R.id.login_rule);
        login_look =findViewById(R.id.login_look);
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
        switch (v.getId()){
            case R.id.login_check:
            case R.id.login_agree:
                break;
            case R.id.login_cancel:
                break;
            case R.id.login_protocol:
                break;
            case R.id.login_rule:
                break;
            case R.id.login_look:
                break;
            case R.id.login_wx:
                startActivity(new Intent(LoginActivity.this,BindPhoneActivity.class));
                break;
        }
    }
}
