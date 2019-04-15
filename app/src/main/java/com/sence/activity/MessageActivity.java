package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;

public class MessageActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.message_open)
    TextView messageOpen;
    @BindView(R.id.message_hd_point)
    ImageView messageHdPoint;
    @BindView(R.id.message_hd_layout)
    RelativeLayout messageHdLayout;
    @BindView(R.id.message_xt_point)
    ImageView messageXtPoint;
    @BindView(R.id.message_xt_layout)
    RelativeLayout messageXtLayout;
    @BindView(R.id.message_tz_point)
    ImageView messageTzPoint;
    @BindView(R.id.message_tz_layout)
    RelativeLayout messageTzLayout;
    @BindView(R.id.message_kf_point)
    ImageView messageKfPoint;
    @BindView(R.id.message_kf_layout)
    RelativeLayout messageKfLayout;

    @Override
    public int onActLayout() {
        return R.layout.activity_message_acitity;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        messageOpen.setOnClickListener(this);
        messageHdLayout.setOnClickListener(this);
        messageXtLayout.setOnClickListener(this);
        messageTzLayout.setOnClickListener(this);
        messageKfLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_open:
                break;
            case R.id.message_hd_layout:
                startActivity(new Intent(MessageActivity.this, MessageHDActivity.class));
                break;
            case R.id.message_xt_layout:
                break;
            case R.id.message_tz_layout:
                break;
            case R.id.message_kf_layout:
                break;
        }
    }
}
