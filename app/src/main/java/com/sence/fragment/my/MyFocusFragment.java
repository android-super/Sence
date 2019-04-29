package com.sence.fragment.my;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.activity.MyFansFocusNoteActivity;
import com.sence.activity.MyInfoActivity;
import com.sence.activity.SearchActivity;
import com.sence.adapter.FansAdapter;
import com.sence.adapter.SearchFriendAdapter;
import com.sence.bean.request.RCancelFocusBean;
import com.sence.bean.request.RFocusBean;
import com.sence.bean.request.RMyFocusBean;
import com.sence.bean.response.PFansBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFocusFragment extends Fragment {
    private String keyword = "";
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private EditText search_content;

    private FansAdapter adapter;
    private int page = 1;

    public MyFocusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_focus, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        smartRefreshLayout = getView().findViewById(R.id.smart_refresh);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        recyclerView = getView().findViewById(R.id.recycle_view);
        search_content = getView().findViewById(R.id.search_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FansAdapter(R.layout.rv_item_fans);
        recyclerView.setAdapter(adapter);
        adapter.setEmptyView(R.layout.empty_focus_my, recyclerView);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initFocusData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initFocusData();
            }
        });
        search_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyword = s.toString();
                initFocusData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Intent intent = new Intent(getActivity(), MyInfoActivity.class);
                intent.putExtra("uid",adapter.getData().get(position).getUid());
                startActivity(intent);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                if (adapter.getData().get(position).getIs_focus().equals("1")) {
                    cancelFocus(adapter.getData().get(position).getUid(), position);
                }
            }
        });
        initFocusData();
    }

    /**
     * 我的关注列表
     */
    private void initFocusData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS_LIST,
                new RMyFocusBean(LoginStatus.getUid(), keyword, page + "")).request(new ApiCallBack<List<PFansBean>>() {
            @Override
            public void onFinish() {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadMore();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PFansBean> o, String msg) {
                if (page == 1) {
                    adapter.setNewData(o);
                } else {
                    adapter.addData(o);
                }
            }
        });
    }

    /**
     * 取消关注
     */
    private void cancelFocus(String to_uid, int position) {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS_CANCEL, new RFocusBean(LoginStatus.getUid(),
                to_uid)).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg) {
                ToastUtils.showShort(msg);
                adapter.remove(position);

            }
        });
    }
}
