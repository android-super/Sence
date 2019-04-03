package com.sence.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sence.R;
import com.sence.adapter.FansAdapter;
import com.sence.bean.request.RFansBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

/**
 * 我的粉丝
 */
public class MyFansActivity extends AppCompatActivity {
    private SmartRefreshLayout smart_refresh;
    private RecyclerView recycle_view;

    private FansAdapter adapter;
    private String keyword = "";//搜索关键字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fans);
        StatusBarUtil.setLightMode(this);
        initView();
        initData();
    }

    /**
     * 我的粉丝数据
     */
    private void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_FANS_LIST,new RFansBean(LoginStatus.getUid(),keyword)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {

            }
        });
    }

    private void initView() {
        smart_refresh = findViewById(R.id.smart_refresh);
        recycle_view = findViewById(R.id.recycle_view);
        smart_refresh.setRefreshHeader(new ClassicsHeader(this));
        smart_refresh.setRefreshFooter(new ClassicsFooter(this));
        recycle_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FansAdapter(R.layout.rv_item_fans);
        recycle_view.setAdapter(adapter);

        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");

        smart_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smart_refresh.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart_refresh.finishRefresh();
            }
        });
    }
}
