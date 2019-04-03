package com.sence.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyInfoRecommendFragment extends Fragment {

    private MyInfoRecommendAdapter myInfoRecommendAdapter;
    private String type;
    private MyInfoNoteAdapter myInfoNoteAdapter;
    private MyInfoServiceAdapter myInfoServiceAdapter;

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
        if(type.equals("1")){
            myInfoRecommendAdapter = new MyInfoRecommendAdapter(getContext());
            LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
            linearLayout.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(linearLayout);
            recyclerView.setAdapter(myInfoRecommendAdapter);
        }else if(type.equals("2")){
            myInfoNoteAdapter = new MyInfoNoteAdapter(getContext());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(myInfoNoteAdapter);
        }else if(type.equals("3")){
            myInfoServiceAdapter = new MyInfoServiceAdapter(getContext());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(myInfoServiceAdapter);
        }
        doHttp();

    }


    private void doHttp() {
        if(type.equals("3")){
            HttpManager.getInstance().PlayNetCode(HttpCode.USER_INFO_DATA_SERVICE, new RMyInfoBean("4", type)).request(new ApiCallBack<PMyInfoServiceBean>() {
                @Override
                public void onFinish() {

                }

                @Override
                public void Message(int code, String message) {

                }

                @Override
                public void onSuccess(PMyInfoServiceBean o, String msg) {
                    Logger.e("msg==========" + msg);
                    myInfoServiceAdapter.setList(o.getOther_info());
                }
            });
            return;
        }
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_INFO_DATA, new RMyInfoBean("4", type)).request(new ApiCallBack<PMyInfoBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMyInfoBean o, String msg) {
                Logger.e("msg==========" + msg);
                if(type.equals("1")){
                    myInfoRecommendAdapter.setList(o.getOther_info());
                }else if(type.equals("2")){
                    myInfoNoteAdapter.setList(o.getOther_info());
                }

            }
        });
    }


}
