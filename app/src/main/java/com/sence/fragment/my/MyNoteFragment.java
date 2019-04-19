package com.sence.fragment.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.FansAdapter;
import com.sence.adapter.MyNoteAdapter;
import com.sence.adapter.NoteAdapter;
import com.sence.bean.request.RUidListBean;
import com.sence.bean.response.PMainNoteBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import java.util.List;

public class MyNoteFragment extends Fragment {
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;

    private int page = 1;
    private MyNoteAdapter adapter;

    public MyNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_note, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        smartRefreshLayout = getView().findViewById(R.id.smart_refresh);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        recyclerView = getView().findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyNoteAdapter(R.layout.rv_item_note);
        recyclerView.setAdapter(adapter);
        adapter.setEmptyView(R.layout.empty_focus_my, recyclerView);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initNoteData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initNoteData();
            }
        });
        initNoteData();
    }

    /**
     * 我的笔记列表
     */
    private void initNoteData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_NOTE_LIST,
                new RUidListBean(LoginStatus.getUid(), page + "")).request(new ApiCallBack<List<PMainNoteBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PMainNoteBean> o, String msg) {
                if (page == 1) {
                    adapter.setNewData(o);
                } else {
                    adapter.addData(o);
                }
            }
        });
    }
}
