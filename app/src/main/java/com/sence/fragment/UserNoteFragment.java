package com.sence.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.activity.MyInfoActivity;
import com.sence.adapter.UserNoteAdapter;
import com.sence.bean.request.RMyInfoBean;
import com.sence.bean.request.RNidBean;
import com.sence.bean.response.PMyInfoBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.view.GridSpacingItemDecoration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 笔记Fragment
 */
public class UserNoteFragment extends Fragment {
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private boolean isShow = true;
    private UserNoteAdapter adapter;
    private int page = 1;
    private String type;
    private ImageView mImg;

    public UserNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_note, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        type = arguments.getString("type");
        initRefresh();
        initData();
    }

    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_INFO_DATA, new RMyInfoBean(LoginStatus.getUid(),type,"",page+"","10")).request(new ApiCallBack<PMyInfoBean>() {
            @Override
            public void onFinish() {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadMore();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PMyInfoBean o, String msg) {
                if(o.getList().size()>0){
                    isShow=false;
                }
                ((MyInfoActivity)getActivity()).setIsShow(isShow);
                if (page == 1) {
                    adapter.setNewData(o.getList());
                } else {
                    adapter.addData(o.getList());
                }
            }
        });
    }


    public void initRefresh() {
        smartRefreshLayout = getView().findViewById(R.id.smart_refresh);
        recyclerView = getView().findViewById(R.id.recycle_view);
        mImg = getView().findViewById(R.id.iv_img_usernote);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, ConvertUtils.dp2px(10),
                true);
        recyclerView.addItemDecoration(gridSpacingItemDecoration);
        adapter = new UserNoteAdapter(R.layout.rv_item_note);
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