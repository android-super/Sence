package com.sence.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sence.R;
import com.sence.adapter.UserDetailAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RUserDetailBean;
import com.sence.bean.response.PUserDetailBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.StatusBarUtil;

import java.util.List;

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
    }

    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_DETAIL, new RUserDetailBean("4", page + "", "10")).request(new ApiCallBack<List<PUserDetailBean>>() {
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
                Logger.e("msg==========" + msg + o.get(0).getImg());
                mUserDetailAdapter.setList(o);
            }
        });
    }

    @Override
    public int onActLayout() {
        return R.layout.activity_userdetail;
    }
}
