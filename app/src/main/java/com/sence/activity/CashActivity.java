package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.blankj.utilcode.util.ToastUtils;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RCashBean;
import com.sence.bean.request.RRegisterBean;
import com.sence.bean.response.PVerifyCodeBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.Arith;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * 提现界面
 */
public class CashActivity extends BaseActivity implements View.OnClickListener {
    public static final int BANK_CODE = 10001;

    @BindView(R.id.cash_name)
    EditText cashName;
    @BindView(R.id.cash_price)
    TextView cashPrice;
    @BindView(R.id.cash_select)
    TextView cashSelect;
    @BindView(R.id.cash_verify)
    TextView cashVerify;
    @BindView(R.id.cash_verify_code)
    EditText cashVerifyCode;
    @BindView(R.id.cash_commit)
    TextView cashCommit;
    @BindView(R.id.cash_money)
    TextView cashMoney;
    @BindView(R.id.cash_img)
    NiceImageView cashImg;

    private Disposable mDisposable;

    private String money;
    private String card_id;
    private String card_phone;
    private String card_img;
    private String code_id;

    private String cash_money;
    private String poundage;

    @Override
    public int onActLayout() {
        return R.layout.activity_cash;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        cash_money = this.getIntent().getStringExtra("cash_money");
        poundage = this.getIntent().getStringExtra("poundage");
        cashSelect.setOnClickListener(this);
        cashVerify.setOnClickListener(this);
        cashCommit.setOnClickListener(this);
        cashMoney.setText("可提现余额为" + cash_money + "元");
        cashName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content_price = s.toString();
                if (!TextUtils.isEmpty(content_price)) {
                    if (Float.parseFloat(content_price) > Float.parseFloat(cash_money)) {
                        content_price = cash_money;
                        cashName.setText(content_price);
                    }
                    float now_price = Float.parseFloat(content_price);
                    float now_poundage = Float.parseFloat(poundage);
                    double all_price = Arith.round(Arith.mul(now_price, now_poundage), 2);
                    cashPrice.setText(all_price + "元");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cash_commit:
                userCash();
                break;
            case R.id.cash_select:
                startActivityForResult(new Intent(CashActivity.this, CardActivity.class), BANK_CODE);
                break;
            case R.id.cash_verify:
                sendVerifyCode();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == BANK_CODE) {
            card_id = data.getStringExtra("card_id");
            card_phone = data.getStringExtra("card_phone");
            card_img = data.getStringExtra("card_img");
            GlideUtils.getInstance().loadNormal(card_img, cashImg);
        }
    }

    /**
     * 提现
     */
    public void userCash() {
        money = cashName.getText().toString();
        if (TextUtils.isEmpty(money)) {
            ToastUtils.showShort("请输入提现金额");
            return;
        }
        if (Integer.parseInt(money) < 100) {
            ToastUtils.showShort("提现金额不能小于100元");
            return;
        }
        String cash_verify_code = cashVerifyCode.getText().toString();
        if (TextUtils.isEmpty(cash_verify_code)) {
            ToastUtils.showShort("验证码不能为空");
            return;
        }
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_CASH,
                new RCashBean(LoginStatus.getUid(), money, card_id, code_id, card_phone, cash_verify_code)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                ToastUtils.showShort(o.toString());
            }
        });
    }

    /**
     * 发送验证码
     */
    private void sendVerifyCode() {
        if (TextUtils.isEmpty(card_phone)) {
            ToastUtils.showShort("请选择银行卡");
            return;
        }
        HttpManager.getInstance().PlayNetCode(HttpCode.SEND_VERIFY_CODE, new RRegisterBean(card_phone)).request(new ApiCallBack<PVerifyCodeBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PVerifyCodeBean o, String msg) {
                code_id = o.getId();
                doLastTime();
            }
        });
    }

    /**
     * 做倒计时
     */
    public void doLastTime() {
        mDisposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) {
                        if (60 - aLong <= 0) {
                            cashVerify.setText("重新获取");
                            mDisposable.dispose();
                            cashVerify.setEnabled(true);
                            cashVerify.setSelected(true);
                        } else {
                            cashVerify.setEnabled(false);
                            cashVerify.setSelected(false);
                            cashVerify.setText("重新获取(" + (60 - aLong) + "s)");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        super.onDestroy();
    }
}
