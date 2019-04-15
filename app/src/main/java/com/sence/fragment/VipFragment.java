package com.sence.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sence.R;
import com.sence.activity.*;
import com.sence.adapter.VipBottomAdapter;
import com.sence.adapter.VipTopAdapter;
import com.sence.bean.request.RUidBean;
import com.sence.bean.response.PUserVipBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.GlideUtils;
import com.sence.utils.LoginStatus;
import com.sence.view.DividerSpacingItemDecoration;
import com.sence.view.GridSpacingItemDecoration;
import com.sence.view.NiceImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VipFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout vip_no_layout;
    private LinearLayout vip_info_layout;
    private NiceImageView vip_head;
    private TextView vip_name;
    private TextView vip_no_content;

    private RelativeLayout vip_yes_layout;
    private TextView vip_price;

    private ImageView vip_pass;
    private TextView vip_exclusive_more;
    private TextView vip_share_more;

    private RecyclerView recycle_view_top;
    private RecyclerView recycle_view_bottom;

    private VipTopAdapter topAdapter;
    private VipBottomAdapter bottomAdapter;


    public VipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vip, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initRefreshLayout();
        initData();
    }

    private void initView() {
        vip_no_layout = getView().findViewById(R.id.vip_no_layout);
        vip_info_layout = getView().findViewById(R.id.vip_info_layout);
        vip_head = getView().findViewById(R.id.vip_head);
        vip_name = getView().findViewById(R.id.vip_name);
        vip_no_content = getView().findViewById(R.id.vip_no_content);

        vip_yes_layout = getView().findViewById(R.id.vip_yes_layout);
        vip_price = getView().findViewById(R.id.vip_price);

        vip_pass = getView().findViewById(R.id.vip_pass);
        vip_exclusive_more = getView().findViewById(R.id.vip_exclusive_more);
        vip_share_more = getView().findViewById(R.id.vip_share_more);

        vip_pass.setOnClickListener(this);
        vip_info_layout.setOnClickListener(this);
        vip_exclusive_more.setOnClickListener(this);
        vip_share_more.setOnClickListener(this);

        if (LoginStatus.isLogin()) {
            GlideUtils.getInstance().loadHead(LoginStatus.getAvatar(), vip_head);
            vip_name.setText(LoginStatus.getName());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vip_pass:
                Intent intent = new Intent(getActivity(), EnjoyVipActivity.class);
                startActivity(intent);
                break;
            case R.id.vip_info_layout:
                intent = new Intent(getActivity(), OpenVipPageActivity.class);
                startActivity(intent);
                break;
            case R.id.vip_exclusive_more:
                intent = new Intent(getActivity(), GoodListActivity.class);
                startActivity(intent);
                break;
            case R.id.vip_share_more:
                intent = new Intent(getActivity(), ServeListActivity.class);
                startActivity(intent);
                break;
        }
    }


    private void initRefreshLayout() {
        recycle_view_top = getView().findViewById(R.id.recycle_view_top);
        recycle_view_bottom = getView().findViewById(R.id.recycle_view_bottom);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recycle_view_top.addItemDecoration(new DividerSpacingItemDecoration(DividerSpacingItemDecoration.HORIZONTAL,
                ConvertUtils.dp2px(15)));
        recycle_view_top.setLayoutManager(linearLayoutManager);
        recycle_view_bottom.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycle_view_bottom.addItemDecoration(new GridSpacingItemDecoration(2, ConvertUtils.dp2px(15), false));
        topAdapter = new VipTopAdapter(R.layout.rv_item_vip_top);
        recycle_view_top.setAdapter(topAdapter);
        bottomAdapter = new VipBottomAdapter(R.layout.rv_item_vip_bottom);
        recycle_view_bottom.setAdapter(bottomAdapter);
        topAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ShopDetailsActivity.class);
                intent.putExtra("id", topAdapter.getData().get(position).getId());
                startActivity(intent);
            }
        });
        bottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ServiceDetailsActivity.class);
                intent.putExtra("id", bottomAdapter.getData().get(position).getId());
                startActivity(intent);
            }
        });

    }


    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_VIP, new RUidBean(LoginStatus.getUid())).request(new ApiCallBack<PUserVipBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PUserVipBean o, String msg) {
                topAdapter.setNewData(o.getGoods());
                bottomAdapter.setNewData(o.getService());

                if (o.getIsMember().equals("0")) {
                    vip_no_layout.setVisibility(View.VISIBLE);
                    vip_yes_layout.setVisibility(View.GONE);
                } else {
                    vip_no_layout.setVisibility(View.GONE);
                    vip_yes_layout.setVisibility(View.VISIBLE);
                }
                vip_no_content.setText(o.getCarousel());
                vip_price.setText(o.getMoney());
            }
        });
    }


}
