package com.sence.activity;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.SearchFriendAdapter;
import com.sence.adapter.SearchShopAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RSearchBean;
import com.sence.bean.response.PSearchBean;
import com.sence.bean.response.PSearchRecommendBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

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
    @BindView(R.id.ll_flow_search)
    LinearLayout llFlowSearch;
    @BindView(R.id.recycle_searchfriend)
    RecyclerView recycleSearchfriend;
    @BindView(R.id.recycle_searchshop)
    RecyclerView recycleSearchshop;
    @BindView(R.id.ll_result_search)
    LinearLayout llResultSearch;
    @BindView(R.id.ll_shop_search)
    LinearLayout llShopSearch;
    @BindView(R.id.ll_friend_search)
    LinearLayout llFriendSearch;
    private List<PSearchRecommendBean> list = new ArrayList<>();
    private SearchFriendAdapter mSearchFriendAdapter;
    private SearchShopAdapter mSearchShopAdapter;

    @Override
    public int onActLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        etContentSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = etContentSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    llFlowSearch.setVisibility(View.GONE);
                    doHttp(content);
                }else{
                    llFlowSearch.setVisibility(View.VISIBLE);
                }
            }
        });
        tvCancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivSearchSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
                llFlowSearch.setVisibility(View.GONE);
                doHttp(data);
            }
        });
    }

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.SEARCH_RECOMMEND).request(new ApiCallBack<List<PSearchRecommendBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PSearchRecommendBean> o, String msg) {
                Logger.e("msg==========" + msg);
                if(o.size()>0){
                    list = o;
                    addList();
                }
            }
        });
        String content = etContentSearch.getText().toString().trim();
        if(!TextUtils.isEmpty(content)){
            doHttp(content);
        }
    }


    private void doHttp(String content) {
        HttpManager.getInstance().PlayNetCode(HttpCode.MAIN_SEARCH, new RSearchBean(content, LoginStatus.getUid())).request(new ApiCallBack<PSearchBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PSearchBean o, String msg) {
                Logger.e("msg==========" + msg);
                if(o.getGoodsList().size()>0){
                    llResultSearch.setVisibility(View.VISIBLE);
                    llShopSearch.setVisibility(View.VISIBLE);
                    mSearchShopAdapter.setList(o.getGoodsList());
                }else{
                    llShopSearch.setVisibility(View.GONE);
                }
                if(o.getUserList().size()>0){
                    llResultSearch.setVisibility(View.VISIBLE);
                    llFriendSearch.setVisibility(View.VISIBLE);
                    mSearchFriendAdapter.setList(o.getUserList());
                }else{
                    llFriendSearch.setVisibility(View.GONE);
                }
            }
        });
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
            tv.setText(list.get(i).getName());
            tv.setMaxLines(1);
            tv.setMaxEms(8);
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setTextColor(Color.parseColor("#333333"));
            tv.setSingleLine();
            tv.setBackgroundResource(R.drawable.shape_search_textbg);
            tv.setLayoutParams(layoutParams);
            flFlowSearch.addView(tv, layoutParams);
        }
    }
}
