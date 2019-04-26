package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.InformAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RInformBean;
import com.sence.bean.response.PInformBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 通知
 */

public class InformActivity extends BaseActivity {

    @BindView(R.id.recycle_inform)
    RecyclerView recycleInform;
    @BindView(R.id.srl_layout_inform)
    SmartRefreshLayout srlLayoutInform;
    @BindView(R.id.iv_img_inform)
    ImageView ivImgInform;
    private InformAdapter mInformAdapter;
    private int page = 1;
    private List<PInformBean> list = new ArrayList<>();

    @Override
    public int onActLayout() {
        return R.layout.activity_inform;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        mInformAdapter = new InformAdapter(InformActivity.this);
        LinearLayoutManager linearLayout = new LinearLayoutManager(InformActivity.this);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recycleInform.setLayoutManager(linearLayout);
        recycleInform.setAdapter(mInformAdapter);
        srlLayoutInform.setRefreshHeader(new ClassicsHeader(InformActivity.this));
        srlLayoutInform.setRefreshFooter(new ClassicsFooter(InformActivity.this));
        srlLayoutInform.setEnableLoadMoreWhenContentNotFull(false);
        srlLayoutInform.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (list.size() < 10) {
                    ToastUtils.showShort("没有更多了！");
                } else {
                    page++;
                    initData();
                }
                srlLayoutInform.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                srlLayoutInform.finishRefresh();
                page = 1;
                list.clear();
                initData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.MESSAGE_INFORM, new RInformBean(LoginStatus.getUid(), page + "", "10")).request(new ApiCallBack<List<PInformBean>>() {
            @Override
            public void onFinish() {
                srlLayoutInform.finishRefresh();
                srlLayoutInform.finishLoadMore();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PInformBean> o, String msg) {
                Logger.e("msg==========" + msg);
                list .addAll(o);
                if (o.size() > 0) {
                    ivImgInform.setVisibility(View.GONE);
                    mInformAdapter.setList(list);
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
}
