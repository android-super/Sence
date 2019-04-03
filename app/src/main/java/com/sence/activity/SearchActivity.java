package com.sence.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sence.R;
import com.sence.adapter.SearchFriendAdapter;
import com.sence.adapter.SearchShopAdapter;
import com.sence.utils.StatusBarUtil;
import com.sence.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 搜索
 */
public class SearchActivity extends AppCompatActivity {

    private FlowLayout mFlowLayout;
    private List<String> list = new ArrayList<>();
    private EditText mContent;
    private LinearLayout mFlow;
    private LinearLayout mResult;
    private SearchFriendAdapter mSearchFriendAdapter;
    private SearchShopAdapter mSearchShopAdapter;
    private RecyclerView mSearchFriend,mSearchShop;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        StatusBarUtil.setLightMode(this);
        initData();

    }

    private void initData() {
        mFlowLayout = findViewById(R.id.fl_flow_search);
        mContent = findViewById(R.id.et_content_search);
        findViewById(R.id.tv_cancel_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFlow = findViewById(R.id.ll_flow_service);
        mResult = findViewById(R.id.ll_result_service);
        mSearchFriend = findViewById(R.id.recycle_searchfriend);
        mSearchShop = findViewById(R.id.recycle_searchshop);
        findViewById(R.id.iv_search_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mContent.getText().toString().trim();
                if(!TextUtils.isEmpty(content)){
                    list.add(content);
                    addList();
                    mFlow.setVisibility(View.GONE);
                    mResult.setVisibility(View.VISIBLE);
                    doHttp();
                }
            }
        });
        for (int i = 0; i <10 ; i++) {
            list.add("酒店年底阿森纳");
            list.add("酒店法撒旦撒年底阿森纳");
            list.add("酒店纳");
            list.add("酒店年底阿萨芬撒森纳");
        }
        addList();
        mSearchFriendAdapter = new SearchFriendAdapter(SearchActivity.this);
        mSearchFriend.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        mSearchFriend.setAdapter(mSearchFriendAdapter);

        mSearchShopAdapter = new SearchShopAdapter(SearchActivity.this);
        mSearchShop.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        mSearchShop.setAdapter(mSearchShopAdapter);

        mFlowLayout.result(new FlowLayout.FlowListener() {
            @Override
            public void gettext(String data) {
                mContent.setText(data);
                mFlow.setVisibility(View.GONE);
                mResult.setVisibility(View.VISIBLE);
            }
        });

    }

    private void doHttp() {

    }

    private void addList() {
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(15, 8, 15, 8);
        if (mFlowLayout != null) {
            mFlowLayout.removeAllViews();
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
            mFlowLayout.addView(tv, layoutParams);
        }
    }
}
