package com.sence.activity;

import android.os.Bundle;

import com.sence.R;
import com.sence.adapter.EnjoyVipAdapter;
import com.sence.utils.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EnjoyVipActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EnjoyVipAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enjoyvip);
        StatusBarUtil.setLightMode(this);
        recyclerView = findViewById(R.id.recycle_enjoyvip);
        adapter = new EnjoyVipAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
