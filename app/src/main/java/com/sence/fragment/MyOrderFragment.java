package com.sence.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.activity.MyOrderActivity;
import com.sence.adapter.MyOrderAdapter;
import com.sence.bean.request.RMyOrderBean;
import com.sence.bean.response.PMyOrderBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TextView mMore;
    private SmartRefreshLayout mSmartRefreshLayout;
    private MyOrderAdapter mMyOrderAdapter;
    private int page=1;
    private int status;
    private List<PMyOrderBean.ListBean> listBeans = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myorder, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        status = arguments.getInt("status",0);
        init();
    }
    

    private void init() {
        mRecyclerView = getView().findViewById(R.id.recycle_myorder);
        mSmartRefreshLayout = getView().findViewById(R.id.srl_more_myorder);
        mMore = getView().findViewById(R.id.tv_more_myorder);
        mMyOrderAdapter = new MyOrderAdapter(getContext());
        mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        loadData();
        mSmartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRecyclerView.setLayoutManager(linearLayout);
        mRecyclerView.setAdapter(mMyOrderAdapter);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if(listBeans.size()==0){
                    ToastUtils.showShort("没有更多了！");
                }else{
                    loadData();
                }
                mSmartRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mSmartRefreshLayout.autoLoadMore();
                page=1;
                loadData();
            }
        });
        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                loadData();
            }
        });
        mMyOrderAdapter.result(new MyOrderAdapter.DeleteOrderListener() {
            @Override
            public void delete(int i) {
                listBeans.remove(i);
                mMyOrderAdapter.setList(listBeans);
                ToastUtils.showShort("取消成功");
                ((MyOrderActivity)getActivity()).setTitlen(i,listBeans.size());
            }
        });

    }




    private void loadData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_LIST, new RMyOrderBean("1",page+"","10",status+"")).request(new ApiCallBack<PMyOrderBean>() {
            @Override
            public void onFinish() {
                mSmartRefreshLayout.finishRefresh();
                mSmartRefreshLayout.finishLoadMore();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMyOrderBean o, String msg) {
                Logger.e("msg==========" + msg);
                ((MyOrderActivity)getActivity()).setTitleNum(o.getAllNum(),o.getWaitPay(),o.getWaitSend(),o.getWaitConfirm(),o.getWaitEvlua());
                listBeans = o.getList();
                if(listBeans.size()>0) {
                    mMyOrderAdapter.setList(listBeans);
                }


            }
        });

    }



}
