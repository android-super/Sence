package com.sence.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.NoteAdapter;
import com.sence.adapter.NoteRecommendAdapter;
import com.sence.bean.request.RNoteDetailBean;
import com.sence.bean.response.PNoteDetailBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.GridSpacingItemDecoration;
import com.sence.view.NiceImageView;

/**
 * 笔记详情
 */
public class NoteDetailActivity extends AppCompatActivity {
    private AppBarLayout app_bar_layout;
    private View tool_view;
    private TextView tool_title;
    private ImageView tool_back;

    private ConvenientBanner note_banner;
    private NiceImageView note_head;
    private TextView note_name;
    private TextView note_support;
    private TextView note_look;
    private TextView note_comment;
    private TextView note_content;
    private RecyclerView note_recycle;
    private TextView note_comment_release;

    private String nid;
    private int page = 1;

    private NoteRecommendAdapter adapter;

    private void initDataView(PNoteDetailBean o) {
        if (o == null) {
            return;
        }
        Glide.with(this).load(Urls.base_url + o.getNote_info().getAvatar()).into(note_head);
        note_name.setText(o.getNote_info().getNick_name());
        note_support.setText(o.getNote_info().getPraise_count());
        note_comment.setText(o.getNote_info().getMessage_count());
        note_look.setText(o.getNote_info().getClick_num());
        note_content.setText(o.getNote_info().getContent());
    }

    private void initView() {
        note_banner = findViewById(R.id.note_banner);
        note_head = findViewById(R.id.note_head);
        note_name = findViewById(R.id.note_name);
        note_support = findViewById(R.id.note_support);
        note_look = findViewById(R.id.note_look);
        note_comment = findViewById(R.id.note_comment);
        note_content = findViewById(R.id.note_content);
        note_recycle = findViewById(R.id.note_recycle);
        note_comment_release = findViewById(R.id.note_comment_release);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        note_recycle.setLayoutManager(gridLayoutManager);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, ConvertUtils.dp2px(10),
                true);
        note_recycle.addItemDecoration(gridSpacingItemDecoration);
        adapter = new NoteRecommendAdapter(R.layout.rv_item_note);
        note_recycle.setAdapter(adapter);

        note_comment_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        nid = this.getIntent().getStringExtra("nid");
        initAppBarLayout();
        initView();
        initData();
    }

    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.NOTE_DETAIL,
                new RNoteDetailBean(LoginStatus.getUid(), nid, page + "")).request(new ApiCallBack<PNoteDetailBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PNoteDetailBean o, String msg) {
                initDataView(o);
                adapter.setNewData(o.getRecommend_note());
            }
        });
    }


    private void initAppBarLayout() {
        tool_back = findViewById(R.id.tool_back);
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
        tool_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
