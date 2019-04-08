package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.rl_head_confirmorder)
    PubTitle rlHeadConfirmorder;
    @BindView(R.id.iv_toaddress_confirmorder)
    ImageView ivToaddressConfirmorder;
    @BindView(R.id.tv_stroename_confirmorder)
    TextView tvStroenameConfirmorder;
    @BindView(R.id.tv_exemption_confirmorder)
    TextView tvExemptionConfirmorder;
    @BindView(R.id.ll_layout_confirmorder)
    LinearLayout llLayoutConfirmorder;
    @BindView(R.id.iv_img_confirmorder)
    ImageView ivImgConfirmorder;
    @BindView(R.id.rl_shopinfo_confirmorder)
    RelativeLayout rlShopinfoConfirmorder;
    @BindView(R.id.iv_to_confirmorder)
    ImageView ivToConfirmorder;
    @BindView(R.id.bt_submint_confirmorder)
    Button btSubmintConfirmorder;
    private BottomSheetDialog mBottomSheetDialog;
    private TextView tvPricePay,tvTimePay,tvMinutePay,tvSecondPay;
    private ImageView ivZhiPay,ivWeiPay,ivBackPay;
    private Button btPayPay;

    @Override
    public int onActLayout() {
        return R.layout.activity_confirmorder;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        bottomSheetDialog();
    }

    @Override
    public void initData() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_zhi_pay:
                ivZhiPay.setImageResource(R.drawable.xuanzhong);
                ivWeiPay.setImageResource(R.drawable.weixuan);
                break;
            case R.id.iv_wei_pay:
                ivWeiPay.setImageResource(R.drawable.xuanzhong);
                ivZhiPay.setImageResource(R.drawable.weixuan);
                break;
            case R.id.bt_pay_pay:
                mBottomSheetDialog.dismiss();
                break;
            case R.id.iv_back_pay:

                break;
        }
    }

    private void bottomSheetDialog() {
        View mView = View.inflate(this, R.layout.bottom_pay_layout, null);
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(mView);
        tvPricePay = mView.findViewById(R.id.tv_price_pay);
        tvTimePay = mView.findViewById(R.id.tv_time_pay);
        tvMinutePay = mView.findViewById(R.id.tv_minute_pay);
        tvSecondPay = mView.findViewById(R.id.tv_second_pay);
        ivZhiPay = mView.findViewById(R.id.iv_zhi_pay);
        ivWeiPay = mView.findViewById(R.id.iv_wei_pay);
        ivBackPay = mView.findViewById(R.id.iv_back_pay);
        btPayPay = mView.findViewById(R.id.bt_pay_pay);
        ivZhiPay.setOnClickListener(this);
        ivWeiPay.setOnClickListener(this);
        ivBackPay.setOnClickListener(this);
        btPayPay.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_address_confirmorder, R.id.bt_submint_confirmorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_address_confirmorder:
                break;
            case R.id.bt_submint_confirmorder:
                break;
        }
    }
}
