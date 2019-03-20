package com.sence.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sence.R;
import com.sence.adapter.MyOrderAdapter;
import com.sence.bean.request.RMyOrderBean;
import com.sence.bean.response.PMyOrderBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView more;
    private SmartRefreshLayout smartRefreshLayout;
    private MyOrderAdapter myOrderAdapter;
    private int page=1;
    private int status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myorder, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        status = arguments.getInt("status");
        initRefresh();
    }

    private void initRefresh() {
        recyclerView = getView().findViewById(R.id.recycle_myorder);
        smartRefreshLayout = getView().findViewById(R.id.srl_more_myorder);
        more = getView().findViewById(R.id.tv_more_myorder);
        myOrderAdapter = new MyOrderAdapter(getContext());
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(myOrderAdapter);
        dohttp();
    }

    private void dohttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.ORDER_LIST, new RMyOrderBean("1",page+"","10",status+"")).request(new ApiCallBack<PMyOrderBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMyOrderBean o, String msg) {
                Logger.e("msg==========" + msg);
                if(o.getList().size()>0){
                    myOrderAdapter.setList(o.getList());
                }else{
                    more.setVisibility(View.GONE);
                }


            }
        });
    }


}
