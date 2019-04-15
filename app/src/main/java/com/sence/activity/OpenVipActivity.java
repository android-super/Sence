package com.sence.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.alipay.sdk.app.PayTask;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RAliPayBean;
import com.sence.bean.request.RWxPayBean;
import com.sence.bean.response.PWxPayBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.StatusBarUtil;
import com.sence.wxapi.WeiXinPayUtils;
import com.sence.zhifubao.PayResult;

import java.util.Map;

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


    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(OpenVipActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(OpenVipActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

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
            case R.id.pay_ali:
                payAli.setSelected(true);
                payWx.setSelected(false);
                break;
            case R.id.pay_wx:
                payAli.setSelected(false);
                payWx.setSelected(true);
                break;
            case R.id.pay_commit:
                if (payAli.isSelected()) {

                } else if (payWx.isSelected()) {

                } else {

                }
                break;
            case R.id.pay_protocol:
                Intent intent = new Intent(OpenVipActivity.this, WebActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 支付宝
     */
    private void aLiPay() {
        HttpManager.getInstance().PlayNetCode(HttpCode.PAY_ALI, new RAliPayBean("", "", "", "")).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(final String o, String msg) {
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(OpenVipActivity.this);
                        Map<String, String> result = alipay.payV2(o, true);
                        Log.i("msp", result.toString());

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });

    }

    /**
     * 微信支付
     */
    private void WxPay() {
        HttpManager.getInstance().PlayNetCode(HttpCode.PAY_ALI, new RWxPayBean("", "", "", "")).request(new ApiCallBack<PWxPayBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(final PWxPayBean o, String msg) {
                    WeiXinPayUtils wxpay = new WeiXinPayUtils(OpenVipActivity.this, o);
                    wxpay.pay();
            }
        });


    }
}
