package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;

/**
 * 确认订单
 */
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_address_confirmorder)
    TextView tvAddressConfirmorder;
    @BindView(R.id.tv_phone_confirmorder)
    TextView tvPhoneConfirmorder;
    @BindView(R.id.tv_name_confirmorder)
    TextView tvNameConfirmorder;
    @BindView(R.id.rl_address_confirmorder)
    RelativeLayout rlAddressConfirmorder;
    @BindView(R.id.tv_shopname_confirmorder)
    TextView tvShopnameConfirmorder;
    @BindView(R.id.tv_price_confirmorder)
    TextView tvPriceConfirmorder;
    @BindView(R.id.tv_num_confirmorder)
    TextView tvNumConfirmorder;
    @BindView(R.id.tv_shopprice_confirmorder)
    TextView tvShoppriceConfirmorder;
    @BindView(R.id.tv_postprice_confirmorder)
    TextView tvPostpriceConfirmorder;
    @BindView(R.id.tv_shopnum_confirmorder)
    TextView tvShopnumConfirmorder;
    @BindView(R.id.tv_maxprice_confirmorder)
    TextView tvMaxpriceConfirmorder;
    @BindView(R.id.tv_sprice_confrimorder)
    TextView tvSpriceConfrimorder;

    @Override
    public int onActLayout() {
        return R.layout.activity_confirmorder;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        rlAddressConfirmorder.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_address_confirmorder:
                startActivity(new Intent(ConfirmOrderActivity.this, ManageAddressActivity.class));
                break;
        }
    }
}
