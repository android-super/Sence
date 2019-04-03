package com.sence.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sence.R;
import com.sence.utils.StatusBarUtil;

/**
 * 开通Vip页面
 */
public class OpenVipActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView pub_back;
    private TextView pub_title;

    private TextView pay_vip, pay_make, pay_share, pay_active;
    private ImageView pay_ali, pay_wx;
    private TextView pay_commit;
    private TextView pay_protocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_vip);
        StatusBarUtil.setLightMode(this);
        initTitle();
        initView();
    }

    private void initTitle() {
        pub_back = findViewById(R.id.pub_back);
        pub_title = findViewById(R.id.pub_title);
        pub_title.setText("开通会员");
        pub_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        pay_vip = findViewById(R.id.pay_vip);
        pay_make = findViewById(R.id.pay_make);
        pay_share = findViewById(R.id.pay_share);
        pay_active = findViewById(R.id.pay_active);
        pay_ali = findViewById(R.id.pay_ali);
        pay_wx = findViewById(R.id.pay_wx);
        pay_commit = findViewById(R.id.pay_commit);
        pay_protocol = findViewById(R.id.pay_protocol);

        pay_vip.setOnClickListener(this);
        pay_make.setOnClickListener(this);
        pay_share.setOnClickListener(this);
        pay_active.setOnClickListener(this);
        pay_ali.setOnClickListener(this);
        pay_wx.setOnClickListener(this);
        pay_commit.setOnClickListener(this);
        pay_protocol.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay_vip:
                break;
            case R.id.pay_make:
                break;
            case R.id.pay_share:
                break;
            case R.id.pay_active:
                break;
            case R.id.pay_ali:
                break;
            case R.id.pay_wx:
                break;
            case R.id.pay_commit:
                break;
            case R.id.pay_protocol:
                break;
        }
    }
}
