package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sence.R;
import com.sence.utils.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView address,phone,name,shopname,price,num,shopnum,postprice,shopprice,maxprice,sprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);
        StatusBarUtil.setLightMode(this);
        address = findViewById(R.id.tv_address_confirmorder);
        phone = findViewById(R.id.tv_phone_confirmorder);
        name = findViewById(R.id.tv_name_confirmorder);
        shopname = findViewById(R.id.tv_shopname_confirmorder);
        price = findViewById(R.id.tv_price_confirmorder);
        num = findViewById(R.id.tv_num_confirmorder);
        shopprice = findViewById(R.id.tv_shopprice_confirmorder);
        postprice = findViewById(R.id.tv_postprice_confirmorder);
        shopnum = findViewById(R.id.tv_shopnum_confirmorder);
        maxprice = findViewById(R.id.tv_maxprice_confirmorder);
        shopnum = findViewById(R.id.tv_shopnum_confirmorder);
        sprice = findViewById(R.id.tv_sprice_confrimorder);
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
