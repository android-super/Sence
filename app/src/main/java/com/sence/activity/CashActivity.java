package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
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

    private Disposable mDisposable;

    private String money;
    private String card_id;
    private String phone;
    private String code_id;

    @Override
    public int onActLayout() {
        return R.layout.activity_cash;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        cashSelect.setOnClickListener(this);
        cashVerify.setOnClickListener(this);
        cashCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cash_commit:
                userCash();
                break;
            case R.id.cash_select:
                startActivityForResult(new Intent(CashActivity.this,CardActivity.class),BANK_CODE);
                break;
            case R.id.cash_verify:
                sendVerifyCode();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == BANK_CODE){
            card_id = data.getStringExtra("card_id");
        }
    }

    /**
     * 提现
     */
    public void userCash() {
        money = cashPrice.getText().toString();
        if (TextUtils.isEmpty(money)){
            ToastUtils.showShort("请输入提现金额");
            return;
        }
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_CASH,
                new RCashBean(LoginStatus.getUid(), money, card_id)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {

            }
        });
    }

    /**
     * 发送验证码
     */
    private void sendVerifyCode() {
        HttpManager.getInstance().PlayNetCode(HttpCode.SEND_VERIFY_CODE, new RRegisterBean(phone)).request(new ApiCallBack<PVerifyCodeBean>() {
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
        super.onDestroy();
        if (mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
