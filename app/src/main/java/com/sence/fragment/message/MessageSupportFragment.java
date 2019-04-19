package com.sence.fragment.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.MessageCommentAdapter;
import com.sence.adapter.MessagelikeAdapter;
import com.sence.bean.request.RMessageHDBean;
import com.sence.bean.response.PMessageHdBean;
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

public class MessageSupportFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MessagelikeAdapter mMessagelikeAdapter;


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
        mMessagelikeAdapter = new MessagelikeAdapter(R.layout.rv_item_message_support);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mMessagelikeAdapter);
        mMessagelikeAdapter.setEmptyView(R.layout.empty_message_hd, mRecyclerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.MESSAGE_LIST, new RMessageHDBean(LoginStatus.getUid(),
                "2")).request(new ApiCallBack<List<PMessageHdBean>>() {
            @Override
            public void onFinish() {
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PMessageHdBean> o, String msg) {
                mMessagelikeAdapter.setNewData(o);
            }
        });

    }


}
