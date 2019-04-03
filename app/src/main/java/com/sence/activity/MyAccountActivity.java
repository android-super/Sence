package com.sence.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sence.R;
import com.sence.bean.response.PAccountBean;
import com.sence.bean.request.RUidBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

/**
 * 我的账户
 */
public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView account_back;
    private TextView account_detail;
    private TextView account_used;
    private TextView account_benefit;

    private TextView account_recharge;
    private TextView account_cash;
    private TextView account_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        initView();
        initData();
    }

    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_ACCOUNT,new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<PAccountBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PAccountBean o, String msg) {
                account_used.setText(o.getMoney());
                account_benefit.setText(o.getPartnerIncome());
            }
        });
    }

    private void initView() {
        account_back = findViewById(R.id.account_back);
        account_detail = findViewById(R.id.account_detail);
        account_used = findViewById(R.id.account_used);
        account_benefit = findViewById(R.id.account_benefit);

        account_recharge = findViewById(R.id.account_recharge);
        account_cash = findViewById(R.id.account_cash);
        account_card = findViewById(R.id.account_card);

        account_back.setOnClickListener(this);
        account_detail.setOnClickListener(this);
        account_recharge.setOnClickListener(this);
        account_cash.setOnClickListener(this);
        account_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_back:
                finish();
                break;
            case R.id.account_detail:
                startActivity(new Intent(MyAccountActivity.this,AccountDetailActivity.class));
                break;
            case R.id.account_recharge:
                startActivity(new Intent(MyAccountActivity.this,RechargeActivity.class));
                break;
            case R.id.account_cash:
                startActivity(new Intent(MyAccountActivity.this,CashActivity.class));
                break;
            case R.id.account_card:
                startActivity(new Intent(MyAccountActivity.this,CardActivity.class));
                break;
        }
    }
}
