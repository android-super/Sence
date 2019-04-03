package com.sence.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sence.R;
import com.sence.adapter.SearchFriendAdapter;
import com.sence.adapter.SearchShopAdapter;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;
import com.sence.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索
 */
public class SearchActivity extends BaseActivity {
    @BindView(R.id.iv_search_search)
    ImageView ivSearchSearch;
    @BindView(R.id.et_content_search)
    EditText etContentSearch;
    @BindView(R.id.tv_cancel_search)
    TextView tvCancelSearch;
    @BindView(R.id.fl_flow_search)
    FlowLayout flFlowSearch;
    @BindView(R.id.ll_flow_service)
    LinearLayout llFlowService;
    @BindView(R.id.recycle_searchfriend)
    RecyclerView recycleSearchfriend;
    @BindView(R.id.recycle_searchshop)
    RecyclerView recycleSearchshop;
    @BindView(R.id.ll_result_service)
    LinearLayout llResultService;

    private List<String> list = new ArrayList<>();
    private SearchFriendAdapter mSearchFriendAdapter;
    private SearchShopAdapter mSearchShopAdapter;

    @Override
    public int onActLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
    }

    public void initData() {
        tvCancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivSearchSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etContentSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    list.add(content);
                    addList();
                    flFlowSearch.setVisibility(View.GONE);
                    llResultService.setVisibility(View.VISIBLE);
                    doHttp();
                }
            }
        });
        for (int i = 0; i < 10; i++) {
            list.add("酒店年底阿森纳");
            list.add("酒店法撒旦撒年底阿森纳");
            list.add("酒店纳");
            list.add("酒店年底阿萨芬撒森纳");
        }
        addList();
        mSearchFriendAdapter = new SearchFriendAdapter(SearchActivity.this);
        recycleSearchfriend.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        recycleSearchfriend.setAdapter(mSearchFriendAdapter);

        mSearchShopAdapter = new SearchShopAdapter(SearchActivity.this);
        recycleSearchshop.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        recycleSearchshop.setAdapter(mSearchShopAdapter);

        flFlowSearch.result(new FlowLayout.FlowListener() {
            @Override
            public void gettext(String data) {
                etContentSearch.setText(data);
                llFlowService.setVisibility(View.GONE);
                llResultService.setVisibility(View.VISIBLE);
            }
        });

    }


    private void doHttp() {

    }

    private void addList() {
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(15, 8, 15, 8);
        if (flFlowSearch != null) {
            flFlowSearch.removeAllViews();
        }
        for (int i = 0; i < list.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(25, 8, 25, 8);
            tv.setText(list.get(i));
            tv.setMaxLines(1);
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setTextColor(Color.parseColor("#333333"));
            tv.setSingleLine();
            tv.setBackgroundResource(R.drawable.shape_search_textbg);
            tv.setLayoutParams(layoutParams);
            flFlowSearch.addView(tv, layoutParams);
        }
    }
}
