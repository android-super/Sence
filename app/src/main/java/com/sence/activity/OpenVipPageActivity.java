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
import com.sence.bean.response.PMoneyBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

public class OpenVipPageActivity extends BaseActivity {
    @BindView(R.id.vip_name)
    TextView vipName;
    @BindView(R.id.vip_describe)
    TextView vipDescribe;
    @BindView(R.id.vip_open)
    ImageView vipOpen;
    @BindView(R.id.vip_back)
    ImageView vipBack;

    private String money;
    private int open_money;

    @Override
    public int onActLayout() {
        return R.layout.activity_open_vip_page;
    }

    @Override
    public void initView() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        money = this.getIntent().getStringExtra("money");
        vipName.setText("Hey " + LoginStatus.getName());
        vipDescribe.setText("黑卡会员平均可省" + money + "/年 更可享受多重VIP特权");
        vipBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        vipOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenVipPageActivity.this, OpenVipActivity.class);
                intent.putExtra("open_money", open_money);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.VIP_OPEN,new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<PMoneyBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMoneyBean o, String msg) {
                open_money = o.getMoney();
            }
        });
    }
}
