package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;

/**
 * 开通Vip页面
 */
public class OpenVipActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.pay_vip)
    TextView payVip;
    @BindView(R.id.pay_make)
    TextView payMake;
    @BindView(R.id.pay_share)
    TextView payShare;
    @BindView(R.id.pay_active)
    TextView payActive;
    @BindView(R.id.pay_ali)
    ImageView payAli;
    @BindView(R.id.pay_wx)
    ImageView payWx;
    @BindView(R.id.pay_commit)
    TextView payCommit;
    @BindView(R.id.pay_protocol)
    TextView payProtocol;

    public void initView() {
        StatusBarUtil.setLightMode(this);
        payVip.setOnClickListener(this);
        payMake.setOnClickListener(this);
        payShare.setOnClickListener(this);
        payActive.setOnClickListener(this);
        payAli.setOnClickListener(this);
        payWx.setOnClickListener(this);
        payCommit.setOnClickListener(this);
        payProtocol.setOnClickListener(this);
    }

    @Override
    public int onActLayout() {
        return R.layout.activity_open_vip;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
