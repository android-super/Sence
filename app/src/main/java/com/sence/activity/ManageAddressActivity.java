package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.ManageAddressAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RManageAddressBean;
import com.sence.bean.response.PManageAddressBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;
import com.sence.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 管理收货地址
 */
public class ManageAddressActivity extends BaseActivity {

    @BindView(R.id.rlv_address_manageaddress)
    RecyclerView rlvAddressManageaddress;
    @BindView(R.id.tv_addaddress_manageaddress)
    TextView tvAddaddressManageaddress;
    @BindView(R.id.srl_layout_manageaddress)
    SmartRefreshLayout srlLayoutManageaddress;

    private ManageAddressAdapter mManageAddressAdapter;
    private int page = 1;
    private List<PManageAddressBean> list = new ArrayList<>();
    private String type;

    @Override
    public int onActLayout() {
        return R.layout.activity_manageaddress;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        tvAddaddressManageaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageAddressActivity.this, AddAddressActivity.class));
            }
        });
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        mManageAddressAdapter = new ManageAddressAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rlvAddressManageaddress.setLayoutManager(linearLayoutManager);
        rlvAddressManageaddress.setAdapter(mManageAddressAdapter);
        srlLayoutManageaddress.setRefreshHeader(new ClassicsHeader(ManageAddressActivity.this));
        srlLayoutManageaddress.setRefreshFooter(new ClassicsFooter(ManageAddressActivity.this));
        srlLayoutManageaddress.setEnableLoadMoreWhenContentNotFull(false);
        srlLayoutManageaddress.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                if(list.size()<10){
                    ToastUtils.showShort("没有更多了！");
                }else{
                    page++;
                    dohttp();
                }
                srlLayoutManageaddress.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                srlLayoutManageaddress.finishRefresh();
                page=1;
                dohttp();
            }
        });
        mManageAddressAdapter.result(new ManageAddressAdapter.DeleteAddressListener() {
            @Override
            public void delete(int i) {
                list.remove(i);
                mManageAddressAdapter.setList(list, type);
                if(list.size()==0){
                    if ("shop".equals(type)) {
                        SharedPreferencesUtil.getInstance().putBoolean("isnull_shopaddress", true);
                    } else if ("shopd".equals(type)) {
                        SharedPreferencesUtil.getInstance().putBoolean("isnull_shopaddress", true);
                    }
                }
            }
        });
    }


    @Override
    public void initData() {
        super.initData();
        dohttp();
    }

    private void dohttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.ADDRESS_LIST, new RManageAddressBean(LoginStatus.getUid(),
                page + "", "10")).request(new ApiCallBack<List<PManageAddressBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(final List<PManageAddressBean> o, String msg) {
                if (o.size() > 0) {
                    list = o;
                    mManageAddressAdapter.setList(o, type);
                }else{
                    if ("shop".equals(type)) {
                        SharedPreferencesUtil.getInstance().putBoolean("isnull_shopaddress", true);
                    } else if ("shopd".equals(type)) {
                        SharedPreferencesUtil.getInstance().putBoolean("isnull_shopaddress", true);
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
