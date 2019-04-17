package com.sence.activity;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.InformAdapter;
import com.sence.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class InformActivity extends BaseActivity {

    @BindView(R.id.recycle_inform)
    RecyclerView recycleInform;
    @BindView(R.id.srl_layout_inform)
    SmartRefreshLayout srlLayoutInform;
    private InformAdapter mInformAdapter;
    private int page =1;
    @Override
    public int onActLayout() {
        return R.layout.activity_inform;
    }

    @Override
    public void initView() {
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
                page++;
//                if(listBeans.size()==0){
//                    ToastUtils.showShort("没有更多了！");
//                }else{
//                    initData();
//                }
                srlLayoutInform.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                srlLayoutInform.finishRefresh();
                page=1;
                initData();
            }
        });
    }

    @Override
    public void initData() {
//        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_LIST, new RMyOrderBean(LoginStatus.getUid(),page+"","10",status+"")).request(new ApiCallBack<PMyOrderBean>() {
//            @Override
//            public void onFinish() {
//                srlLayoutInform.finishRefresh();
//                srlLayoutInform.finishLoadMore();
//            }
//
//            @Override
//            public void Message(int code, String message) {
//
//            }
//
//            @Override
//            public void onSuccess(PMyOrderBean o, String msg) {
//                Logger.e("msg==========" + msg);
//                listBeans = o.getList();
//                if(o.size()>0) {
//                    mInformAdapter.setList(listBeans);
//                }
//
//
//            }
//        });
    }


}
