package com.sence.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ConvertUtils;
import com.sence.R;
import com.sence.adapter.MemberAdapter;
import com.sence.utils.StatusBarUtil;
import com.sence.view.GridSpacingItemDecoration;

/**
 * V群成员
 */
public class MemberActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private MemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        StatusBarUtil.setLightMode(this);
        recyclerView = findViewById(R.id.recycle_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(5, ConvertUtils.dp2px(20), true));
        adapter = new MemberAdapter(R.layout.rv_item_member);
        recyclerView.setAdapter(adapter);
    }
}
