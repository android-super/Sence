package com.sence.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.CommentAdapter;
import com.sence.adapter.ContentGoodAdapter;
import com.sence.adapter.GoodsAdapter;
import com.sence.bean.request.RContentDetailBean;
import com.sence.bean.response.PContentDetailBean;
import com.sence.fragment.CommentFragment;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;

import java.util.List;

/**
 * 内容详情
 */
public class ContentDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private AppBarLayout app_bar_layout;
    private View tool_view;
    private ImageView tool_back;
    private LinearLayout tool_title_layout;
    private ImageView tool_share;
    private NiceImageView tool_head;
    private TextView tool_name;
    private ImageView tool_focus;
    private TextView tool_content_focus;

    private NestedScrollView content_nested;

    private ImageView content_support;
    private TextView content_support_num;
    private TextView content_comment;
    private TextView content_buy;
    private TextView content_buy_num;
    private TextView content_title;

    private NiceImageView content_head;
    private TextView content_focus_tv;
    private ImageView content_focus;
    private TextView content_name;
    private TextView content_describe;

    private WebView content_web;
    private RecyclerView content_recycle;

    private String nid;
    private ContentGoodAdapter adapter;

    private List<PContentDetailBean.NoteInfoBean.GoodsInfoBean> goodsInfoBeans;

    private void initDataView(PContentDetailBean o) {
        if (o == null) {
            return;
        }
        PContentDetailBean.NoteInfoBean noteInfoBean = o.getNote_info();
        Glide.with(this).load(Urls.base_url + noteInfoBean.getAvatar()).into(tool_head);
        tool_name.setText(noteInfoBean.getNick_name());
        Glide.with(this).load(Urls.base_url + noteInfoBean.getAvatar()).into(content_head);
        content_name.setText(noteInfoBean.getNick_name());
        content_describe.setText(noteInfoBean.getAutograph());
        content_title.setText(noteInfoBean.getTitle());

        if (noteInfoBean.getIs_like().equals("1")) {
            content_support.setImageResource(R.drawable.recommend_dianzan_y);
        } else {
            content_support.setImageResource(R.drawable.recommend_dianzan);
        }
        if (noteInfoBean.getIs_focus().equals("1")) {
            tool_focus.setVisibility(View.VISIBLE);
            tool_content_focus.setVisibility(View.GONE);
            content_focus.setVisibility(View.VISIBLE);
            content_focus_tv.setVisibility(View.GONE);
        } else {
            tool_focus.setVisibility(View.GONE);
            tool_content_focus.setVisibility(View.VISIBLE);
            content_focus.setVisibility(View.GONE);
            content_focus_tv.setVisibility(View.VISIBLE);
        }
        content_support_num.setText("赞·" + noteInfoBean.getPraise_count());
        content_comment.setText("评论·" + noteInfoBean.getMessage_count());

        content_buy_num.setText(noteInfoBean.getGoods_info().size() + "");
        goodsInfoBeans = noteInfoBean.getGoods_info();
        adapter.setNewData(noteInfoBean.getGoods_info());
        content_web.loadData(noteInfoBean.getContent(), "text/html; charset=UTF-8", null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        nid = this.getIntent().getStringExtra("nid");
        initAppBarLayout();
        initView();
        initData();
    }

    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.CONTENT_DETAIL,
                new RContentDetailBean(LoginStatus.getUid(), nid)).request(new ApiCallBack<PContentDetailBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PContentDetailBean o, String msg) {
                initDataView(o);
            }
        });
    }


    private void initView() {
        content_support = findViewById(R.id.content_support);
        content_support_num = findViewById(R.id.content_support_num);
        content_buy = findViewById(R.id.content_buy);
        content_buy_num = findViewById(R.id.content_buy_num);

        content_head = findViewById(R.id.content_head);
        content_focus_tv = findViewById(R.id.content_focus_tv);
        content_focus = findViewById(R.id.content_focus);
        content_name = findViewById(R.id.content_name);
        content_describe = findViewById(R.id.content_describe);
        content_comment = findViewById(R.id.content_comment);

        content_title = findViewById(R.id.content_title);
        content_web = findViewById(R.id.content_web);
        content_recycle = findViewById(R.id.content_recycle);

        content_recycle.setLayoutManager(new LinearLayoutManager(ContentDetailActivity.this));
        adapter = new ContentGoodAdapter(R.layout.rv_item_goods);
        content_recycle.setAdapter(adapter);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CommentFragment.newInstance(nid,
                "2")).commit();

        content_comment.setOnClickListener(this);
        content_head.setOnClickListener(this);
        tool_head.setOnClickListener(this);
        content_support_num.setOnClickListener(this);
        content_support.setOnClickListener(this);
        content_buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_comment:
                showCommentDialog();
                break;
            case R.id.content_head:
                startActivity(new Intent(this, MyInfoActivity.class));
                break;
            case R.id.tool_head:
                startActivity(new Intent(this, MyInfoActivity.class));
                break;
            case R.id.content_support_num:

                break;
            case R.id.content_support:

                break;
            case R.id.content_buy:
                showGoodDialog();
                break;
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void initAppBarLayout() {
        app_bar_layout = findViewById(R.id.app_bar_layout);
        tool_view = findViewById(R.id.tool_view);
        tool_title_layout = findViewById(R.id.tool_title_layout);
        tool_share = findViewById(R.id.tool_share);
        tool_back = findViewById(R.id.tool_back);
        content_nested = findViewById(R.id.content_nested);

        tool_head = findViewById(R.id.tool_head);
        tool_name = findViewById(R.id.tool_name);
        tool_focus = findViewById(R.id.tool_focus);
        tool_content_focus = findViewById(R.id.tool_content_focus);

        tool_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        recycle_view.setLayoutManager(new LinearLayoutManager(this));
        recycle_view.setAdapter(adapter);
        adapter.setNewData(goodsInfoBeans);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }


}
