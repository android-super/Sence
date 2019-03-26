package com.sence.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ConvertUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.CommentAdapter;
import com.sence.adapter.GoodsAdapter;
import com.sence.utils.StatusBarUtil;

/**
 * 内容详情
 */
public class ContentDetailActivity extends AppCompatActivity {
    private AppBarLayout app_bar_layout;
    private View tool_view;

    private LinearLayout tool_title_layout;
    private ImageView tool_share;
    private NestedScrollView content_nested;

    private TextView content_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initView() {
        app_bar_layout = findViewById(R.id.app_bar_layout);
        tool_view = findViewById(R.id.tool_view);
        tool_title_layout = findViewById(R.id.tool_title_layout);
        tool_share = findViewById(R.id.tool_share);
        content_nested = findViewById(R.id.content_nested);

        content_comment = findViewById(R.id.content_comment);
        content_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();
                showGoodDialog();
            }
        });

        tool_view.setAlpha(0);
        tool_title_layout.setAlpha(0);
        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                tool_view.setAlpha(alpha);
                tool_share.setAlpha(1 - alpha);
            }
        });
        content_nested.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY && scrollY > ConvertUtils.dp2px(35)) {
                    float title_alpha = (float) scrollY / (float) ConvertUtils.dp2px(35);
                    tool_title_layout.setAlpha(title_alpha);
                } else if (scrollY < oldScrollY && scrollY < ConvertUtils.dp2px(35)) {
                    float title_alpha = (float) scrollY / (float) ConvertUtils.dp2px(35);
                    tool_title_layout.setAlpha(title_alpha);
                }
            }
        });
    }

    public void showCommentDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_comment, null);
        RecyclerView recycle_view = view.findViewById(R.id.recycle_view);
        CommentAdapter adapter = new CommentAdapter(R.layout.rv_item_comment);
        recycle_view.setAdapter(adapter);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    public void showGoodDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_goods, null);
        RecyclerView recycle_view = view.findViewById(R.id.recycle_view);
        GoodsAdapter adapter = new GoodsAdapter(R.layout.rv_item_goods);
        recycle_view.setAdapter(adapter);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}
