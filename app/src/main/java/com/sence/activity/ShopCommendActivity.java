package com.sence.activity;

import android.os.Bundle;
import android.view.View;

import com.sence.R;
import com.sence.adapter.ShopCommendAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCommendActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ShopCommendAdapter mShopCommendAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcommend);
        initData();
    }
    private void initData() {
        mRecyclerView = findViewById(R.id.recycle_shopcommend);
        mShopCommendAdapter = new ShopCommendAdapter(ShopCommendActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopCommendActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mShopCommendAdapter);
        findViewById(R.id.iv_back_shopcommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
