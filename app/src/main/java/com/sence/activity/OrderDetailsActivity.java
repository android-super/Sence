package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sence.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        findViewById(R.id.rl_address_orderdetails).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_address_orderdetails:
                startActivity(new Intent(OrderDetailsActivity.this,ManageAddressActivity.class));
                break;
        }
    }
}
