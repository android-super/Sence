package com.sence.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.KindLeftAdapter;
import com.sence.adapter.KindRightAdapter;
import com.sence.bean.request.RBusAddBean;
import com.sence.bean.request.RIdListBean;
import com.sence.bean.response.PGoodListBean;
import com.sence.bean.response.PKindBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KindFragment extends Fragment {
    private RecyclerView recycle_view_horizontal;
    private RecyclerView recycle_view_vertical;
    private SmartRefreshLayout smart_refresh;

    private KindLeftAdapter leftAdapter;
    private KindRightAdapter rightAdapter;

    private String id;
    private int page = 1;
    private int size = 10;

    public KindFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initKindList();
    }

    private void initKindList() {
        leftAdapter = new KindLeftAdapter(R.layout.rv_item_kind_left);
        HttpManager.getInstance().PlayNetCode(HttpCode.KIND_GOODS).request(new ApiCallBack<List<PKindBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PKindBean> o, String msg) {
                leftAdapter.setNewData(o);
                if (leftAdapter.getData().size() > 0) {
                    id = leftAdapter.getData().get(0).getId();
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kind, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        smart_refresh = getView().findViewById(R.id.smart_refresh);
        smart_refresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        smart_refresh.setRefreshFooter(new ClassicsFooter(getActivity()));
        recycle_view_horizontal = getView().findViewById(R.id.recycle_view_horizontal);
        recycle_view_vertical = getView().findViewById(R.id.recycle_view_vertical);

        recycle_view_horizontal.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle_view_vertical.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycle_view_horizontal.setAdapter(leftAdapter);
        rightAdapter = new KindRightAdapter(R.layout.rv_item_kind_right);
        recycle_view_vertical.setAdapter(rightAdapter);

        smart_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initGoodList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initGoodList();
            }
        });


        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                leftAdapter.setPosition(position);
                page = 1;
                initGoodList();
            }
        });
        rightAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.item_bus) {
                    addBus(rightAdapter.getData().get(position).getId());
                }
            }
        });
        initGoodList();
    }

    /**
     * 获取分类商品列表
     */
    private void initGoodList() {
        HttpManager.getInstance().PlayNetCode(HttpCode.KIND_GOODS_LIST, new RIdListBean(id, page + "", size + "")).request(new ApiCallBack<List<PGoodListBean>>() {
            @Override
            public void onFinish() {
                smart_refresh.finishRefresh();
                smart_refresh.finishLoadMore();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PGoodListBean> o, String msg) {
                if (page == 1) {
                    rightAdapter.setNewData(o);
                } else {
                    rightAdapter.addData(o);
                }
            }
        });
    }

    /**
     * 加入购物车
     */
    private void addBus(String gid) {
        HttpManager.getInstance().PlayNetCode(HttpCode.BUS_ADD, new RBusAddBean(gid, LoginStatus.getUid())).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                ToastUtils.showShort("成功加入购物车");
            }
        });
    }
}
