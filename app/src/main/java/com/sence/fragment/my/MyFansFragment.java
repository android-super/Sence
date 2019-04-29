package com.sence.fragment.my;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
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
import com.sence.bean.request.RFansBean;
import com.sence.bean.request.RFocusBean;
import com.sence.bean.response.PFansBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.umeng.commonsdk.debug.E;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFansFragment extends Fragment {
    private String keyword = "";
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private EditText search_content;

    private FansAdapter adapter;
    private int page = 1;

    public MyFansFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_fans, container, false);
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
        adapter.setEmptyView(R.layout.empty_focus_my, recyclerView);
        recyclerView.setAdapter(adapter);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initFansData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initFansData();
            }
        });
        search_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyword = s.toString();
                if (!TextUtils.isEmpty(keyword)) {
                    initFansData();
                }
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
                } else {
                    toFocus(adapter.getData().get(position).getUid(), position);
                }
            }
        });
        initFansData();
    }

    /**
     * 我的粉丝列表
     */
    public void initFansData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FANS_LIST,
                new RFansBean(LoginStatus.getUid(), keyword, page + "")).request(new ApiCallBack<List<PFansBean>>() {
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
     * 关注
     *
     * @param to_uid
     */
    public void toFocus(String to_uid, int position) {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FOCUS, new RFocusBean(LoginStatus.getUid(), to_uid)).request(new ApiCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(String o, String msg) {
                ToastUtils.showShort(msg);
                adapter.getData().get(position).setIs_focus("1");
                adapter.notifyDataSetChanged();
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
                adapter.getData().get(position).setIs_focus("0");
                adapter.notifyDataSetChanged();
            }
        });
    }
}
