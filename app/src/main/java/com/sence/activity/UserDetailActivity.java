package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sence.R;
import com.sence.adapter.UserDetailAdapter;
import com.sence.bean.request.RUserDetailBean;
import com.sence.bean.response.PUserDetailBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.StatusBarUtil;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * 用户明细
 */
public class UserDetailActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private UserDetailAdapter mUserDetailAdapter;
    private int page = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);
        StatusBarUtil.setLightMode(this);
        initData();
    }

    private void initData() {
        TextView mTitle = findViewById(R.id.pub_title);
        mTitle.setText("明细");
        ImageView mBack = findViewById(R.id.pub_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView = findViewById(R.id.recycle_userdetail);
        mSmartRefreshLayout = findViewById(R.id.srl_userdetail);
        mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(UserDetailActivity.this));
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(UserDetailActivity.this));
        mUserDetailAdapter = new UserDetailAdapter(UserDetailActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserDetailActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mUserDetailAdapter);
        doHttp();
    }

    private void doHttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_DETAIL, new RUserDetailBean( "4",page+"","10")).request(new ApiCallBack<List<PUserDetailBean>>() {
            @Override
            public void onFinish() {
                mSmartRefreshLayout.finishLoadMore();
                mSmartRefreshLayout.finishRefresh();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PUserDetailBean> o, String msg) {
                Logger.e("msg==========" + msg+o.get(0).getImg());
                mUserDetailAdapter.setList(o);
            }
        });
    }
}
