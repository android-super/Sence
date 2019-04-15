package com.sence.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sence.R;
import com.sence.activity.ContentDetailActivity;
import com.sence.adapter.CommentAdapter;
import com.sence.bean.request.RCommentListBean;
import com.sence.bean.request.RCommentSupportBean;
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
    private TextView comment_look;

    private String rid;
    private String type;
    private int page = 1;

    private CommentAdapter commentAdapter;


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
        comment_look = getView().findViewById(R.id.comment_look);

        comment_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        commentAdapter = new CommentAdapter(R.layout.rv_item_comment);
        comment_recycle.setAdapter(commentAdapter);

        comment_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ContentDetailActivity) getActivity()).showCommentDialog();
            }
        });
        comment_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ContentDetailActivity) getActivity()).showCommentDialog();
            }
        });
        commentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CommentSupport(commentAdapter.getData().get(position).getId(), position);
            }
        });

        initData();
    }

    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_LIST,
                new RCommentListBean(LoginStatus.getUid(), rid, type, page + "")).request(new ApiCallBack<List<PCommentBean>>() {
            @Override
            public void onFinish() {
                comment_num.setText("(" + commentAdapter.getData().size() + ")");
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PCommentBean> o, String msg) {
                if (page == 1) {
                    commentAdapter.setNewData(o);
                } else {
                    commentAdapter.addData(o);
                }

            }
        });
    }

    public void CommentSupport(String mid, final int position) {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_SUPPORT,
                new RCommentSupportBean(LoginStatus.getUid(), mid)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {
                int support_num = Integer.parseInt(commentAdapter.getData().get(position).getLike_num());
                if (commentAdapter.getData().get(position).getIs_like().equals("0")) {
                    commentAdapter.getData().get(position).setIs_like("1");
                    support_num = support_num + 1;
                } else {
                    commentAdapter.getData().get(position).setIs_like("0");
                    support_num = support_num - 1;
                }
                commentAdapter.getData().get(position).setLike_num(support_num + "");
                commentAdapter.notifyDataSetChanged();
            }
        });
    }
}
