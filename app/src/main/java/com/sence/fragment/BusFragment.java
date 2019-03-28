package com.sence.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.BusBottomAdapter;
import com.sence.adapter.BusTopAdapter;
import com.sence.adapter.KindRightAdapter;
import com.sence.bean.request.RUidBean;
import com.sence.bean.request.RUidListBean;
import com.sence.bean.response.PBusBean;
import com.sence.bean.response.PBusRecommendBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.view.GridSpacingItemDecoration;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusFragment extends Fragment {
    private SmartRefreshLayout smart_refresh;
    private RecyclerView recycle_view;
    private RecyclerView recycle_view_bottom;

    private BusTopAdapter topAdapter;
    private BusBottomAdapter bottomAdapter;

    private int page = 1;

    public BusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        smart_refresh = getView().findViewById(R.id.smart_refresh);
        recycle_view = getView().findViewById(R.id.recycle_view);
        recycle_view_bottom = getView().findViewById(R.id.recycle_view_bottom);
        smart_refresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        smart_refresh.setRefreshFooter(new ClassicsFooter(getActivity()));
        recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recycle_view_bottom.setLayoutManager(gridLayoutManager);
        topAdapter = new BusTopAdapter(R.layout.rv_item_bus_top);
        recycle_view.setAdapter(topAdapter);
        bottomAdapter = new BusBottomAdapter(R.layout.rv_item_bus_bottom);
        recycle_view_bottom.setAdapter(bottomAdapter);
        recycle_view_bottom.addItemDecoration(new GridSpacingItemDecoration(2, ConvertUtils.dp2px(10), false));
        View top_head = LayoutInflater.from(getActivity()).inflate(R.layout.rv_item_bus_head, null);
        topAdapter.addHeaderView(top_head);
        topAdapter.setEmptyView(R.layout.rv_item_bus_empty, recycle_view);

        smart_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initRecommendData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initBusData();
                initRecommendData();
            }
        });

        topAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.item_name_select) {
                    if (topAdapter.getData().get(position).isSelect()) {
                        topAdapter.getData().get(position).setSelect(false);
                    } else {
                        topAdapter.getData().get(position).setSelect(true);
                    }
                    topAdapter.notifyDataSetChanged();
                }
            }
        });

        initBusData();
        initRecommendData();

    }

    /**
     * 记载推荐数据
     */
    private void initRecommendData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BUS_RECOMMEND, new RUidListBean(LoginStatus.getUid(), page +
                "")).request(new ApiCallBack<List<PBusRecommendBean>>() {
            @Override
            public void onFinish() {
                smart_refresh.finishLoadMore();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PBusRecommendBean> o, String msg) {
                if (page == 1) {
                    bottomAdapter.setNewData(o);
                } else {
                    bottomAdapter.addData(o);
                }
            }
        });
    }

    /**
     * 加载购物车数据
     */
    private void initBusData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BUS_GOODS, new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<PBusBean>() {
            @Override
            public void onFinish() {
                smart_refresh.finishRefresh();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PBusBean o, String msg) {
                topAdapter.setNewData(o.getCart());
            }
        });
    }
}
