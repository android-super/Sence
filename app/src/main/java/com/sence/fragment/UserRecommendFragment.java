package com.sence.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.activity.MyInfoActivity;
import com.sence.adapter.UserRecommendAdapter;
import com.sence.bean.request.RMyInfoBean;
import com.sence.bean.request.RNidBean;
import com.sence.bean.response.PMyInfoBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserRecommendFragment extends Fragment {
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;

    private UserRecommendAdapter adapter;
    private int page = 1;
    private int size = 10;
    private String type;
    private ImageView mImg;
    private boolean isShow = true;
    private String uid= "";

    public UserRecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_recommend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        type = arguments.getString("type");
        uid = arguments.getString("uid");
        initRefresh();
        initData();
    }

    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_INFO_DATA, new RMyInfoBean(LoginStatus.getUid(),type,uid,page+"","10")).request(new ApiCallBack<PMyInfoBean>() {
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
                    isShow = false;
                }
                ((MyInfoActivity)getActivity()).setRecommendShowImg(isShow);
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
        mImg = getView().findViewById(R.id.iv_img_userrecommend);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mImg.getLayoutParams();
                //获取当前控件的布局对象
                Log.i("aaaaaa", dx+"=1="+dy);
                layoutParams.height=dy;//设置当前控件布局的高度
                mImg.setLayoutParams(layoutParams);
            }
        });

        adapter = new UserRecommendAdapter(R.layout.rv_item_recommend);
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
                if (view.getId() == R.id.item_support || view.getId() == R.id.item_support_img) {
                    support(position,adapter.getData().get(position).getNid());

                }
            }
        });
    }

    /**
     * 点赞
     * @param position
     * @param nid
     */
    public void support(final int position, String nid) {
        HttpManager.getInstance().PlayNetCode(HttpCode.SUPPORT_NOTE_RECOMMEND, new RNidBean(LoginStatus.getUid(), nid)).request(new ApiCallBack() {
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
