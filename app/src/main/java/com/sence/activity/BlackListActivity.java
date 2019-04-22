package com.sence.activity;

import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.BlackListAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RBlackListBean;
import com.sence.bean.response.PBlackListBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BlackListActivity extends BaseActivity {


    @BindView(R.id.recycle_blacklist)
    RecyclerView recycleBlacklist;
    private BlackListAdapter blackListAdapter;
    private int page = 1;
    @Override
    public int onActLayout() {
        return R.layout.activity_black_list;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        blackListAdapter = new BlackListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleBlacklist.setLayoutManager(linearLayoutManager);
        recycleBlacklist.setAdapter(blackListAdapter);
    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BLACK_LIST,
                new RBlackListBean(LoginStatus.getUid(), page+"","10")).request(new ApiCallBack<List<PBlackListBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PBlackListBean> o, String msg){
                Logger.e("msg==========" + msg);
                blackListAdapter.setList(o);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
