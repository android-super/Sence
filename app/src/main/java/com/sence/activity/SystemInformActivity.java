package com.sence.activity;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.SystemInformAdapter;
import com.sence.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SystemInformActivity extends BaseActivity {

    @BindView(R.id.recycle_systeminform)
    RecyclerView recycleSysteminform;
    @BindView(R.id.srl_layout_systeminform)
    SmartRefreshLayout srlLayoutSysteminform;
    private SystemInformAdapter mSystemInformAdapter;
    private int page = 1;
    @Override
    public int onActLayout() {
        return R.layout.activity_system_inform;
    }

    @Override
    public void initView() {
        mSystemInformAdapter = new SystemInformAdapter(SystemInformActivity.this);
        LinearLayoutManager linearLayout = new LinearLayoutManager(SystemInformActivity.this);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recycleSysteminform.setLayoutManager(linearLayout);
        recycleSysteminform.setAdapter(mSystemInformAdapter);
        srlLayoutSysteminform.setRefreshHeader(new ClassicsHeader(SystemInformActivity.this));
        srlLayoutSysteminform.setRefreshFooter(new ClassicsFooter(SystemInformActivity.this));
        srlLayoutSysteminform.setEnableLoadMoreWhenContentNotFull(false);
        srlLayoutSysteminform.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
//                if(listBeans.size()==0){
//                    ToastUtils.showShort("没有更多了！");
//                }else{
//                    initData();
//                }
                srlLayoutSysteminform.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                srlLayoutSysteminform.finishRefresh();
                page=1;
                initData();
            }
        });
    }

    @Override
    public void initData() {

    }


}
