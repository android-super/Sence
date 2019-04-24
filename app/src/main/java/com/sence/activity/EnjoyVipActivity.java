package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.EnjoyVipAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RShopCommendBean;
import com.sence.bean.response.PEnjoyVipBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 尊享会员
 */
public class EnjoyVipActivity extends BaseActivity {
    @BindView(R.id.tv_price_enjoyvip)
    TextView tvPriceEnjoyvip;
    @BindView(R.id.recycle_enjoyvip)
    RecyclerView recycleEnjoyvip;
    @BindView(R.id.srl_layout_enjoyvip)
    SmartRefreshLayout srlLayoutEnjoyvip;


    private EnjoyVipAdapter mEnjoyVipAdapter;
    private int page = 1;
    private List<PEnjoyVipBean.ServiceBean> list = new ArrayList<>();

    @Override
    public int onActLayout() {
        return R.layout.activity_enjoyvip;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        tvPriceEnjoyvip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnjoyVipActivity.this, UserDetailActivity.class));
            }
        });
        mEnjoyVipAdapter = new EnjoyVipAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleEnjoyvip.setLayoutManager(gridLayoutManager);
        recycleEnjoyvip.setAdapter(mEnjoyVipAdapter);
        srlLayoutEnjoyvip.setRefreshHeader(new ClassicsHeader(this));
        srlLayoutEnjoyvip.setRefreshFooter(new ClassicsFooter(this));
        srlLayoutEnjoyvip.setEnableLoadMoreWhenContentNotFull(false);
        srlLayoutEnjoyvip.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(list.size()<10){
                    ToastUtils.showShort("没有更多了！");
                }else{
                    page++;
                    initData();
                }
                srlLayoutEnjoyvip.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                srlLayoutEnjoyvip.finishLoadMore();
                page = 1;
                initData();
            }
        });
    }

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_ENJOY_VIP, new RShopCommendBean(LoginStatus.getUid(),
                page + "", "10")).request(new ApiCallBack<PEnjoyVipBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PEnjoyVipBean o, String msg) {
                Logger.e("msg==========" + msg);
                tvPriceEnjoyvip.setText("￥" + o.getMoney());
                list = o.getService();
                if (list.size() > 0) {
                    mEnjoyVipAdapter.setList(o.getService());
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
