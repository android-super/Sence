package com.sence.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.CommentAdapter;
import com.sence.bean.request.RCommentListBean;
import com.sence.bean.response.PCommentBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {
    private static final String TAG_RID = "rid";
    private static final String TAG_TYPE = "type";

    private TextView comment_num;
    private TextView comment_write;
    private RecyclerView comment_recycle;
    private SmartRefreshLayout content_smart;


    private String rid;
    private String type;
    private int page = 1;

    private CommentAdapter adapter;


    public CommentFragment() {
    }

    public static CommentFragment newInstance(String rid, String type) {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putString(TAG_RID, rid);
        args.putString(TAG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rid = getArguments().getString(TAG_RID);
            type = getArguments().getString(TAG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comment_num = getView().findViewById(R.id.comment_num);
        comment_write = getView().findViewById(R.id.comment_write);
        comment_recycle = getView().findViewById(R.id.comment_recycle);
        content_smart = getView().findViewById(R.id.content_smart);

        content_smart.setRefreshHeader(new ClassicsHeader(getActivity()));
        content_smart.setRefreshFooter(new ClassicsFooter(getActivity()));

        comment_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommentAdapter(R.layout.rv_item_comment);
        comment_recycle.setAdapter(adapter);

        content_smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
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

        initData();
    }

    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_LIST,
                new RCommentListBean(LoginStatus.getUid(), rid, type, page + "")).request(new ApiCallBack<List<PCommentBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PCommentBean> o, String msg) {
                if (page == 1) {
                    adapter.setNewData(o);
                } else {
                    adapter.addData(o);
                }
            }
        });
    }
}
