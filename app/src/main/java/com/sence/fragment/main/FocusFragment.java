package com.sence.fragment.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.MainFocusAdapter;
import com.sence.adapter.MainRecommendFocusAdapter;
import com.sence.bean.request.RNidBean;
import com.sence.bean.request.RUidListBean;
import com.sence.bean.response.PMainBean;
import com.sence.bean.response.PMainFocusBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.view.GridSpacingItemDecoration;

import java.util.List;

/**
 * 关注Fragment
 */
public class FocusFragment extends Fragment {
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;

    private RecyclerView empty_recycle;
    private View empty_view;

    private MainFocusAdapter adapter;
    private MainRecommendFocusAdapter recommendFocusAdapter;
    private int page = 1;

    public FocusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_focus, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRefresh();
        initData();
    }

    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.MAIN_FOCUS,
                new RUidListBean(LoginStatus.getUid(), page + "")).request(new ApiCallBack<PMainBean>() {
            @Override
            public void onFinish() {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadMore();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMainBean o, String msg) {
                if (page == 1) {
                    adapter.setNewData(o.getNote_list());
                } else {
                    adapter.addData(o.getNote_list());
                }
                recommendFocusAdapter.setNewData(o.getGoodess_recommend());
            }
        });
    }

    public void initRefresh() {
        smartRefreshLayout = getView().findViewById(R.id.smart_refresh);
        recyclerView = getView().findViewById(R.id.recycle_view);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new MainFocusAdapter(R.layout.rv_item_main_focus);
        recyclerView.setAdapter(adapter);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, ConvertUtils.dp2px(10)
                , true);
        recyclerView.addItemDecoration(gridSpacingItemDecoration);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                if (view.getId() == R.id.item_support) {
                    support(position, adapter.getData().get(position).getNid());
                }
            }
        });

        initEmptyView();
    }

    private void initEmptyView() {
        empty_view = LayoutInflater.from(getActivity()).inflate(R.layout.empty_main_focus, null);
        adapter.setEmptyView(empty_view);
        empty_recycle = empty_view.findViewById(R.id.recycle_view);
        empty_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recommendFocusAdapter = new MainRecommendFocusAdapter(R.layout.rv_item_main_focus);
        empty_recycle.setAdapter(recommendFocusAdapter);
    }

    /**
     * 点赞
     *
     * @param position
     * @param nid
     */
    public void support(final int position, String nid) {
        HttpManager.getInstance().PlayNetCode(HttpCode.SUPPORT_NOTE_RECOMMEND, new RNidBean(LoginStatus.getUid(),
                nid)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                int support_num = Integer.parseInt(adapter.getData().get(position).getPraise_count());
                if (adapter.getData().get(position).getIs_like().equals("0")) {
                    adapter.getData().get(position).setIs_like("1");
                    support_num = support_num + 1;
                } else {
                    adapter.getData().get(position).setIs_like("0");
                    support_num = support_num - 1;
                }
                adapter.getData().get(position).setPraise_count(support_num + "");
                adapter.notifyDataSetChanged();

            }
        });
    }
}
