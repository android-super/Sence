package com.sence.activity;

import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.ServeAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RListBean;
import com.sence.bean.response.PServeBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.StatusBarUtil;
import com.sence.view.GridSpacingItemDecoration;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 服务列表-大V分享
 */
public class ServeListActivity extends BaseActivity {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;

    private ServeAdapter adapter;

    private int page = 1;

    @Override
    public int onActLayout() {
        return R.layout.activity_serve_list;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        smartRefresh.setRefreshHeader(new ClassicsHeader(this));
        smartRefresh.setRefreshFooter(new ClassicsFooter(this));
        recycleView.setLayoutManager(new GridLayoutManager(this, 2));
        recycleView.addItemDecoration(new GridSpacingItemDecoration(2, ConvertUtils.dp2px(15), true));
        adapter = new ServeAdapter(R.layout.rv_item_vip_bottom);
        recycleView.setAdapter(adapter);
        smartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
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
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Intent intent = new Intent(ServeListActivity.this, ShopDetailsActivity.class);
                intent.putExtra("id", adapter.getData().get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_SERVE_LIST, new RListBean(page + "")).request(new ApiCallBack<List<PServeBean>>() {
            @Override
            public void onFinish() {
                smartRefresh.finishRefresh();
                smartRefresh.finishLoadMore();
            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PServeBean> o, String msg) {
                if (page == 1) {
                    adapter.setNewData(o);
                } else {
                    adapter.addData(o);
                }
            }
        });
    }
}
