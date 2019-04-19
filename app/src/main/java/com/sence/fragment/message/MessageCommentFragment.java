package com.sence.fragment.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

public class MessageCommentFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MessageCommentAdapter mMessageCommentAdapter;

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
        mMessageCommentAdapter = new MessageCommentAdapter(R.layout.rv_item_message_comment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mMessageCommentAdapter);
        mMessageCommentAdapter.setEmptyView(R.layout.empty_message_hd, mRecyclerView);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.MESSAGE_LIST, new RMessageHDBean(LoginStatus.getUid(),
                "1")).request(new ApiCallBack<List<PMessageHdBean>>() {
            @Override
            public void onFinish() {
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PMessageHdBean> o, String msg) {
                mMessageCommentAdapter.setNewData(o);
            }
        });

    }


}
