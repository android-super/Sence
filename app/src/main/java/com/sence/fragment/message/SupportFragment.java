package com.sence.fragment.message;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.NoteAdapter;
import com.sence.bean.request.RNidBean;
import com.sence.bean.request.RUidListBean;
import com.sence.bean.response.PMainBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.view.GridSpacingItemDecoration;

/**
 * 笔记Fragment
 */
public class SupportFragment extends Fragment {
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;

    private NoteAdapter adapter;
    private int page = 1;

    public SupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRefresh();
        initData();
    }

    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.MAIN_NOTE,
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
            }
        });
    }


    public void initRefresh() {
        smartRefreshLayout = getView().findViewById(R.id.smart_refresh);
        recyclerView = getView().findViewById(R.id.recycle_view);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, ConvertUtils.dp2px(10),
                true);
        recyclerView.addItemDecoration(gridSpacingItemDecoration);
        adapter = new NoteAdapter(R.layout.rv_item_note);
        adapter.setEmptyView(R.layout.empty_main_recommend_note, recyclerView);
        recyclerView.setAdapter(adapter);
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