package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.sence.R;
import com.sence.adapter.NoteRecommendAdapter;
import com.sence.base.BaseActivity;
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
public class NoteDetailActivity extends BaseActivity {
    @BindView(R.id.note_banner)
    ConvenientBanner noteBanner;
    @BindView(R.id.tool_view)
    View toolView;
    @BindView(R.id.tool_back)
    ImageView toolBack;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.note_head)
    NiceImageView noteHead;
    @BindView(R.id.note_name)
    TextView noteName;
    @BindView(R.id.note_support)
    TextView noteSupport;
    @BindView(R.id.note_look)
    TextView noteLook;
    @BindView(R.id.note_comment)
    TextView noteComment;
    @BindView(R.id.note_content)
    TextView noteContent;
    @BindView(R.id.note_recycle)
    RecyclerView noteRecycle;
    @BindView(R.id.note_comment_release)
    TextView noteCommentRelease;

    private String nid;
    private int page = 1;

    private NoteRecommendAdapter adapter;

    @Override
    public int onActLayout() {
        return R.layout.activity_note_detail;
    }

    private void initDataView(PNoteDetailBean o) {
        if (o == null) {
            return;
        }
        Glide.with(this).load(Urls.base_url + o.getNote_info().getAvatar()).into(noteHead);
        noteName.setText(o.getNote_info().getNick_name());
        noteSupport.setText(o.getNote_info().getPraise_count());
        noteComment.setText(o.getNote_info().getMessage_count());
        noteLook.setText(o.getNote_info().getClick_num());
        noteContent.setText(o.getNote_info().getContent());
    }

    public void initView() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        nid = this.getIntent().getStringExtra("nid");
        initAppBarLayout();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        noteRecycle.setLayoutManager(gridLayoutManager);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, ConvertUtils.dp2px(10),
                true);
        noteRecycle.addItemDecoration(gridSpacingItemDecoration);
        adapter = new NoteRecommendAdapter(R.layout.rv_item_note);
        noteRecycle.setAdapter(adapter);

        noteCommentRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void initData() {
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
        toolView.setAlpha(0);
        toolTitle.setAlpha(0);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                toolView.setAlpha(alpha);
                toolTitle.setAlpha(alpha);
            }
        });
        toolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
