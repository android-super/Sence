package com.sence.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sence.R;
import com.sence.activity.MyOrderActivity;
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
        init();
    }
    

    private void init() {
        recyclerView = getView().findViewById(R.id.recycle_myorder);
        smartRefreshLayout = getView().findViewById(R.id.srl_more_myorder);
        more = getView().findViewById(R.id.tv_more_myorder);
        myOrderAdapter = new MyOrderAdapter(getContext());
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        Log.i("aaa",status+"");
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(myOrderAdapter);


    }



    private static final String TAG = "LazyFragment";
    private static final String NAME = "name";

    private String titlename ;
    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUiVisible;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint -- " + isVisibleToUser);
        if (isVisibleToUser) {
            isUiVisible = true;
            lazyLoad();
        } else {
            isUiVisible = false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titlename = getArguments().getString(NAME);
        }
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
        isViewCreated = true;
        lazyLoad();
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,
        // 必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUiVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUiVisible = false;
        }
    }

    private void loadData() {
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
                ((MyOrderActivity)getActivity()).setTitleNum(o.getAllNum(),o.getWaitPay(),o.getWaitSend(),o.getWaitConfirm(),o.getWaitEvlua());
                if(o.getList().size()>0){
                    myOrderAdapter.setList(o.getList());
                    more.setVisibility(View.VISIBLE);
                }else{
                    more.setVisibility(View.GONE);
                }


            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");
        //页面销毁,恢复标记
        isViewCreated = false;
        isUiVisible = false;
    }


}
