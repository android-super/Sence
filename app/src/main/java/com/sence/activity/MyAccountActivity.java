package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RUidBean;
import com.sence.bean.response.PAccountBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

/**
 * 我的账户
 */
public class MyAccountActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.account_back)
    ImageView accountBack;
    @BindView(R.id.account_detail)
    TextView accountDetail;
    @BindView(R.id.account_used)
    TextView accountUsed;
    @BindView(R.id.account_benefit)
    TextView accountBenefit;
    @BindView(R.id.account_recharge)
    TextView accountRecharge;
    @BindView(R.id.account_cash)
    TextView accountCash;
    @BindView(R.id.account_card)
    TextView accountCard;

    private String money;
    private String poundage;

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_ACCOUNT, new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<PAccountBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PAccountBean o, String msg) {
                money = o.getMoney();
                poundage = o.getPoundage();
                accountUsed.setText(o.getMoney());
                accountBenefit.setText(o.getPartnerIncome());
            }
        });
    }

    @Override
    public int onActLayout() {
        return R.layout.activity_my_account;
    }

    public void initView() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        accountBack.setOnClickListener(this);
        accountDetail.setOnClickListener(this);
        accountRecharge.setOnClickListener(this);
        accountCash.setOnClickListener(this);
        accountCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_back:
                finish();
                break;
            case R.id.account_detail:
                startActivity(new Intent(MyAccountActivity.this, UserDetailActivity.class));
                break;
            case R.id.account_recharge://目前不要了
                startActivity(new Intent(MyAccountActivity.this, RechargeActivity.class));
                break;
            case R.id.account_cash:
                Intent intent = new Intent(MyAccountActivity.this, CashActivity.class);
                intent.putExtra("cash_money",money);
                intent.putExtra("poundage",poundage);
                startActivity(intent);
                break;
            case R.id.account_card:
                startActivity(new Intent(MyAccountActivity.this, CardActivity.class));
                break;
        }
    }
}
