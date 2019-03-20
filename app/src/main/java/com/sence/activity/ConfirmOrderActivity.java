package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sence.R;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);
        findViewById(R.id.rl_address_confirmorder).setOnClickListener(this);
        findViewById(R.id.iv_back_confirmorder).setOnClickListener(this);
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
