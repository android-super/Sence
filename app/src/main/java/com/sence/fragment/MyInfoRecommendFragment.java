package com.sence.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.MyInfoNoteAdapter;
import com.sence.adapter.MyInfoRecommendAdapter;
import com.sence.adapter.MyInfoServiceAdapter;
import com.sence.bean.request.RMyInfoBean;
import com.sence.bean.response.PMyInfoBean;
import com.sence.bean.response.PMyInfoServiceBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public class MyInfoRecommendFragment extends Fragment {

    private MyInfoRecommendAdapter myInfoRecommendAdapter;
    private String type;
    private MyInfoNoteAdapter myInfoNoteAdapter;
    private MyInfoServiceAdapter myInfoServiceAdapter;
    private int page=1;
    private SmartRefreshLayout mSmartRefreshLayout;
    private List<PMyInfoServiceBean.ListBean> list = new ArrayList<>();
    private List<PMyInfoBean.ListBean> listOther = new ArrayList<>();

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
        mSmartRefreshLayout = getView().findViewById(R.id.srl_layout_myinforecommend);
        if("1".equals(type)){
            myInfoRecommendAdapter = new MyInfoRecommendAdapter(getContext());
            LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
            linearLayout.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(linearLayout);
            recyclerView.setAdapter(myInfoRecommendAdapter);
        }else if("2".equals(type)){
            myInfoNoteAdapter = new MyInfoNoteAdapter(getContext());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(myInfoNoteAdapter);
        }else if("3".equals(type)){
            myInfoServiceAdapter = new MyInfoServiceAdapter(getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(myInfoServiceAdapter);
        }
        mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        mSmartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if("3".equals(type)){
                    if(list.size()==0){
                        ToastUtils.showShort("没有更多了！");
                    }else{
                        doHttp();
                    }
                }else{
                    if(listOther.size()==0){
                        ToastUtils.showShort("没有更多了！");
                    }else{
                        doHttp();
                    }
                }

                mSmartRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mSmartRefreshLayout.finishRefresh();
                page = 1;
                doHttp();
            }
        });
        doHttp();

    }


    private void doHttp() {
        if("3".equals(type)){
            HttpManager.getInstance().PlayNetCode(HttpCode.USER_INFO_DATA_SERVICE, new RMyInfoBean(LoginStatus.getUid(), type,LoginStatus.getUid(),page+"","10")).request(new ApiCallBack<PMyInfoServiceBean>() {
                @Override
                public void onFinish() {

                }

                @Override
                public void Message(int code, String message) {

                }

                @Override
                public void onSuccess(PMyInfoServiceBean o, String msg) {
                    Logger.e("msg==========" + msg);
                    list = o.getList();
                    if(o.getList().size()>0){
                        myInfoServiceAdapter.setList(o.getList());
                    }

                }
            });
            return;
        }
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_INFO_DATA, new RMyInfoBean(LoginStatus.getUid(), type,"",page+"","10")).request(new ApiCallBack<PMyInfoBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMyInfoBean o, String msg) {
                Logger.e("msg==========" + msg);
                listOther = o.getList();
                if("1".equals(type)){
                    myInfoRecommendAdapter.setList(o.getList());
                }else if("2".equals(type)){
                    myInfoNoteAdapter.setList(o.getList());
                }

            }
        });
    }

    private DeleteServiceListener listener;

    public void result(DeleteServiceListener listener) {
        this.listener = listener;
    }

    public interface DeleteServiceListener {
        void delete();
    }
}
