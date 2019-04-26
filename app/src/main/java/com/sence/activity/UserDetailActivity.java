package com.sence.activity;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.UserDetailAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RUserDetailBean;
import com.sence.bean.response.PUserDetailBean;
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

/**
 * 用户明细
 */
public class UserDetailActivity extends BaseActivity {

    @BindView(R.id.recycle_userdetail)
    RecyclerView recycleUserdetail;
    @BindView(R.id.srl_userdetail)
    SmartRefreshLayout srlUserdetail;

    private UserDetailAdapter mUserDetailAdapter;
    private int page = 1;
    private List<PUserDetailBean> listBean = new ArrayList<>();

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        srlUserdetail.setRefreshHeader(new ClassicsHeader(UserDetailActivity.this));
        srlUserdetail.setRefreshFooter(new ClassicsFooter(UserDetailActivity.this));
        mUserDetailAdapter = new UserDetailAdapter(UserDetailActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserDetailActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleUserdetail.setLayoutManager(linearLayoutManager);
        recycleUserdetail.setAdapter(mUserDetailAdapter);
        srlUserdetail.setEnableLoadMoreWhenContentNotFull(false);
        srlUserdetail.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(listBean.size()<10){
                    ToastUtils.showShort("没有更多了！");
                }else{
                    page++;
                    initData();
                }
                srlUserdetail.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                srlUserdetail.finishRefresh();
                page=1;
                listBean.clear();
                initData();
            }
        });
    }

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_DETAIL, new RUserDetailBean(LoginStatus.getUid(), page + "", "10")).request(new ApiCallBack<List<PUserDetailBean>>() {
            @Override
            public void onFinish() {
                srlUserdetail.finishLoadMore();
                srlUserdetail.finishRefresh();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PUserDetailBean> o, String msg) {
                Logger.e("msg==========" + msg );
                listBean.addAll(o) ;
                if(listBean.size()>0){
                    mUserDetailAdapter.setList(listBean);
                }

            }
        });
    }

    @Override
    public int onActLayout() {
        return R.layout.activity_userdetail;
    }
}
