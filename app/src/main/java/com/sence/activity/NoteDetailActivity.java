package com.sence.activity;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.utils.StatusBarUtil;

/**
 * 笔记详情
 */
public class NoteDetailActivity extends AppCompatActivity {
    private AppBarLayout app_bar_layout;
    private View tool_view;
    private TextView tool_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        initView();
    }

    private void initView() {
        app_bar_layout = findViewById(R.id.app_bar_layout);
        tool_view = findViewById(R.id.tool_view);
        tool_title = findViewById(R.id.tool_title);
        tool_view.setAlpha(0);
        tool_title.setAlpha(0);
        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                tool_view.setAlpha(alpha);
                tool_title.setAlpha(alpha);
            }
        });
    }
}
