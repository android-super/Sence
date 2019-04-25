package com.sence.fragment.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.activity.MessageActivity;
import com.sence.activity.chat.ui.ChatMsgActivity;
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
        mMessagePrivateAdapter = new MessagePrivateAdapter(R.layout.rv_item_message_private);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mMessagePrivateAdapter);
        mMessagePrivateAdapter.setEmptyView(R.layout.empty_message_hd, mRecyclerView);
        mMessagePrivateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ChatMsgActivity.class);
                intent.putExtra("u_to", mMessagePrivateAdapter.getData().get(position).getTo_uid());
                intent.putExtra("chat_id", mMessagePrivateAdapter.getData().get(position).getId());
                intent.putExtra("name", mMessagePrivateAdapter.getData().get(position).getNick_name());
                intent.putExtra("u_avatar", mMessagePrivateAdapter.getData().get(position).getAvatar());
                startActivity(intent);
            }
        });
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
                mMessagePrivateAdapter.setNewData(o);
            }
        });
    }
}
