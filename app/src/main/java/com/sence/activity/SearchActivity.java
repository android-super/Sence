package com.sence.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.view.FlowLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.tv_clear_search)
    TextView tvClearSearch;
    @BindView(R.id.ll_notlayout_search)
    LinearLayout llNotlayoutSearch;
    private SearchFriendAdapter mSearchFriendAdapter;
    private SearchShopAdapter mSearchShopAdapter;

    @Override
    public int onActLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        etContentSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String content = etContentSearch.getText().toString().trim();
                    if (!TextUtils.isEmpty(content)) {
                        String histroy = LoginStatus.getHistroy();
                        if (TextUtils.isEmpty(histroy)) {
                            SharedPreferencesUtil.getInstance().putString("histroy", content);
                            String[] split = {content};
                            flFlowSearch.addList(split);
                        } else {
                            SharedPreferencesUtil.getInstance().putString("histroy", histroy + "," + content);
                            String histroytext = LoginStatus.getHistroy();
                            tvClearSearch.setVisibility(View.VISIBLE);
                            String[] split = histroytext.split(",");
                            flFlowSearch.addList(split);
                        }
                        llResultSearch.setVisibility(View.VISIBLE);
                        llFlowSearch.setVisibility(View.GONE);
                        tvClearSearch.setVisibility(View.VISIBLE);
                        doHttp(content);
                    } else {
                        llNotlayoutSearch.setVisibility(View.GONE);
                        llResultSearch.setVisibility(View.GONE);
                        llFlowSearch.setVisibility(View.VISIBLE);
                    }
                }
                return false;
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
                String content = etContentSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    String histroy = LoginStatus.getHistroy();
                    if (TextUtils.isEmpty(histroy)) {
                        SharedPreferencesUtil.getInstance().putString("histroy", content);
                        String[] split = {content};
                        flFlowSearch.addList(split);
                    } else {
                        SharedPreferencesUtil.getInstance().putString("histroy", histroy + "," + content);
                        String histroytext = LoginStatus.getHistroy();
                        tvClearSearch.setVisibility(View.VISIBLE);
                        String[] split = histroytext.split(",");
                        flFlowSearch.addList(split);
                    }
                    llResultSearch.setVisibility(View.VISIBLE);
                    llFlowSearch.setVisibility(View.GONE);
                    tvClearSearch.setVisibility(View.VISIBLE);
                    doHttp(content);
                } else {
                    llNotlayoutSearch.setVisibility(View.GONE);
                    llResultSearch.setVisibility(View.GONE);
                    llFlowSearch.setVisibility(View.VISIBLE);
                }
            }
        });
        mSearchFriendAdapter = new SearchFriendAdapter(SearchActivity.this);
        recycleSearchfriend.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        recycleSearchfriend.setAdapter(mSearchFriendAdapter);

        mSearchShopAdapter = new SearchShopAdapter(SearchActivity.this);
        recycleSearchshop.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        recycleSearchshop.setAdapter(mSearchShopAdapter);
        flFlowSearch.result(new FlowLayout.SearchListener() {
            @Override
            public void search(String data) {
                etContentSearch.setText(data);
                llResultSearch.setVisibility(View.VISIBLE);
                llFlowSearch.setVisibility(View.GONE);
                tvClearSearch.setVisibility(View.VISIBLE);
                doHttp(data);
            }
        });
    }

    public void initData() {
        String histroy = LoginStatus.getHistroy();
        if (TextUtils.isEmpty(histroy)) {
            tvClearSearch.setVisibility(View.GONE);
        } else {
            tvClearSearch.setVisibility(View.VISIBLE);
            String[] split = histroy.split(",");
            flFlowSearch.addList(split);
        }
        String content = etContentSearch.getText().toString().trim();
        if (!TextUtils.isEmpty(content)) {
            doHttp(content);
        }
    }


    private void doHttp(final String content) {
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
                if (o.getGoodsList().size() > 0) {
                    llShopSearch.setVisibility(View.VISIBLE);
                    mSearchShopAdapter.setList(o.getGoodsList());
                } else {
                    llShopSearch.setVisibility(View.GONE);
                }
                if (o.getUserList().size() > 0) {
                    llFriendSearch.setVisibility(View.VISIBLE);
                    mSearchFriendAdapter.setList(o.getUserList());
                } else {
                    llFriendSearch.setVisibility(View.GONE);
                }
                if(o.getGoodsList().size()==0&&o.getUserList().size()==0){
                    llNotlayoutSearch.setVisibility(View.VISIBLE);
                }else{
                    llNotlayoutSearch.setVisibility(View.GONE);
                }

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_clear_search)
    public void onViewClicked() {
        SharedPreferencesUtil.getInstance().putString("histroy", "");
        flFlowSearch.clear();
        tvClearSearch.setVisibility(View.GONE);
    }
}
