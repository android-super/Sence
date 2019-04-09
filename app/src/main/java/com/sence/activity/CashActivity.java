package com.sence.activity;

import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RCashBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

/**
 * 提现界面
 */
public class CashActivity extends BaseActivity {
    private String money;
    private String bank_id;

    @Override
    public int onActLayout() {
        return R.layout.activity_cash;
    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_CASH,
                new RCashBean(LoginStatus.getUid(), money, bank_id)).request(new ApiCallBack() {
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
}
