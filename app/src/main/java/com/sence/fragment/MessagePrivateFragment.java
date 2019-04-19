package com.sence.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.MessagePrivateAdapter;
import com.sence.bean.request.RUidBean;
import com.sence.bean.response.PPrivateChatBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MessagePrivateFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private int status;
    private List<PPrivateChatBean> listBeans = new ArrayList<>();
    private MessagePrivateAdapter mMessagePrivateAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messagehd, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }


    private void init() {
        mRecyclerView = getView().findViewById(R.id.recycle_messagehd);
        mMessagePrivateAdapter = new MessagePrivateAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mMessagePrivateAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.PRIVATE_CHAT_LIST, new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<List<PPrivateChatBean>>() {
            @Override
            public void onFinish() {
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PPrivateChatBean> o, String msg) {
                Logger.e("msg==========" + msg);
                listBeans = o;



            }
        });

    }


}
