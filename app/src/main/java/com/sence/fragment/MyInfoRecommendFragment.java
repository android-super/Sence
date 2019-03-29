package com.sence.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.MyInfoRecommendAdapter;
import com.sence.bean.request.RMyInfoBean;
import com.sence.bean.response.PMyInfoBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyInfoRecommendFragment extends Fragment {

    private MyInfoRecommendAdapter myInfoRecommendAdapter;
    private String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myinfo_recommend, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        type = arguments.getString("type");
        init();
    }


    private void init() {
        RecyclerView recyclerView = getView().findViewById(R.id.recycle_myinforecommend);
        myInfoRecommendAdapter = new MyInfoRecommendAdapter(getContext());
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(myInfoRecommendAdapter);


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
            doHttp();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUiVisible = false;
        }
    }

    private void doHttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_INFO_DATA, new RMyInfoBean("4","1")).request(new ApiCallBack<PMyInfoBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMyInfoBean o, String msg) {
                Logger.e("msg==========" + msg);
                myInfoRecommendAdapter.setList(o.getOther_info());
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
