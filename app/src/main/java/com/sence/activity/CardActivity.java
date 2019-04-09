package com.sence.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sence.R;
import com.sence.adapter.BankCardAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RUidListBean;
import com.sence.bean.response.PBankCardBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

import java.util.List;

/**
 * 银行卡界面
 */
public class CardActivity extends BaseActivity {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;

    private BankCardAdapter adapter;
    private int page = 1;

    @Override
    public int onActLayout() {
        return R.layout.activity_card;
    }

    @Override
    public void initView() {
        smartRefresh.setEnableRefresh(false);
        smartRefresh.setEnableLoadMore(false);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BankCardAdapter(R.layout.rv_item_bank_card);
        recycleView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BANK_CARD,new RUidListBean(LoginStatus.getUid(),page+"")).request(new ApiCallBack<List<PBankCardBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PBankCardBean> o, String msg) {

            }
        });
    }
}
