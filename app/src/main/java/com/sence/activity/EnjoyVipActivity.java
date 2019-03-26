package com.sence.activity;

import android.os.Bundle;
import android.view.View;

import com.sence.R;
import com.sence.adapter.EnjoyVipAdapter;
import com.sence.utils.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EnjoyVipActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private EnjoyVipAdapter mEnjoyVipAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enjoyvip);
        StatusBarUtil.setLightMode(this);
        findViewById(R.id.iv_back_enjoyvip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView = findViewById(R.id.recycle_enjoyvip);
        mEnjoyVipAdapter = new EnjoyVipAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mEnjoyVipAdapter);
    }
}
