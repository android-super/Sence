package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.bean.request.ROrderDetailsBean;
import com.sence.bean.response.POrderDetailsBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mState,mNumber,mTime,mName,mPhone,mAddress,mCoupon,mPostPrice,mTaxPrice,mMoney,mSprice,mShopPrice;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        initData();

    }

    private void initData() {
        findViewById(R.id.rl_address_orderdetails).setOnClickListener(this);
        findViewById(R.id.iv_back_orderdetails).setOnClickListener(this);
        mState = findViewById(R.id.tv_state_orderdetails);
        mNumber = findViewById(R.id.tv_number_orderdetails);
        mTime = findViewById(R.id.tv_time_orderdetails);
        mName = findViewById(R.id.tv_name_orderdetails);
        mPhone = findViewById(R.id.tv_phone_orderdetails);
        mAddress = findViewById(R.id.tv_address_orderdetails);
        mCoupon = findViewById(R.id.tv_coupon_orderdetails);
        mPostPrice = findViewById(R.id.tv_postprice_orderdetails);
        mTaxPrice = findViewById(R.id.tv_taxprice_orderdetails);
        mShopPrice = findViewById(R.id.tv_shopprice_orderdetails);
        mMoney = findViewById(R.id.tv_money_orderdetails);
        mSprice = findViewById(R.id.tv_sprice_orderdetails);

        doHttp();
    }

    private void doHttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_DETAIL, new ROrderDetailsBean(id,"1")).request(new ApiCallBack<POrderDetailsBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(POrderDetailsBean o, String msg) {
                Logger.e("msg==========" + msg+o.getNeedpay());
                mState.setText(o.getStatus());
                mNumber.setText(o.getOid());
                mTime.setText(o.getAddtime());
                mName.setText(o.getAddress().getUsername());
                mPhone.setText(o.getAddress().getPhone());
                mAddress.setText(o.getAddress().getAddress());
                mShopPrice.setText("￥"+o.getGmoney());
                mCoupon.setText("-￥"+o.getCmoney());
                mPostPrice.setText("+￥"+o.getPmoney());
                mTaxPrice.setText("+￥0.00");
                mMoney.setText("￥"+o.getNeedpay());
                mSprice.setText("￥"+o.getNeedpay());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_address_orderdetails:
                startActivity(new Intent(OrderDetailsActivity.this,ManageAddressActivity.class));
                break;
            case R.id.iv_back_orderdetails:
                finish();
                break;
        }
    }
}
