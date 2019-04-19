package com.sence.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RUidBean;
import com.sence.bean.response.PMessageBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

import butterknife.BindView;
/**
 * 消息中心
 */

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
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.MESSAGE_CENTRE, new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<PMessageBean>() {
            @Override
            public void onFinish() {
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMessageBean o, String msg) {
                Logger.e("msg==========" + msg);
                if("0".equals(o.getMessage_red())){
                    messageHdPoint.setVisibility(View.GONE);
                }
                if("0".equals(o.getSystem_msg_red())){
                    messageXtPoint.setVisibility(View.GONE);
                }
                if("0".equals(o.getSystem_ntc_red())){
                    messageTzPoint.setVisibility(View.GONE);
                }
            }
        });
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
                startActivity(new Intent(MessageActivity.this, SystemInformActivity.class));
                break;
            case R.id.message_tz_layout:
                startActivity(new Intent(MessageActivity.this, InformActivity.class));
                break;
            case R.id.message_kf_layout:
                break;
        }
    }
}
