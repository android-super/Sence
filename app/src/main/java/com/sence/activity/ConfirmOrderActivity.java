package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sence.R;
import com.sence.utils.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mAddress,mPhone,mName,mShopname,mPrice,mNum,mShopNum,mPostPrice,mShopPrice,mMaxPrice,mSprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);
        StatusBarUtil.setLightMode(this);
        mAddress = findViewById(R.id.tv_address_confirmorder);
        mPhone = findViewById(R.id.tv_phone_confirmorder);
        mName = findViewById(R.id.tv_name_confirmorder);
        mShopname = findViewById(R.id.tv_shopname_confirmorder);
        mPrice = findViewById(R.id.tv_price_confirmorder);
        mNum = findViewById(R.id.tv_num_confirmorder);
        mShopPrice = findViewById(R.id.tv_shopprice_confirmorder);
        mPostPrice = findViewById(R.id.tv_postprice_confirmorder);
        mShopNum = findViewById(R.id.tv_shopnum_confirmorder);
        mMaxPrice = findViewById(R.id.tv_maxprice_confirmorder);
        mSprice = findViewById(R.id.tv_sprice_confrimorder);
        findViewById(R.id.rl_address_confirmorder).setOnClickListener(this);
        findViewById(R.id.iv_back_confirmorder).setOnClickListener(this);
        dohttp();
    }
    private void dohttp() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_address_confirmorder:
                startActivity(new Intent(ConfirmOrderActivity.this,ManageAddressActivity.class));
                break;
            case R.id.iv_back_confirmorder:
                finish();
                break;
        }
    }
}
