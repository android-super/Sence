package com.sence.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.sence.utils.StatusBarUtil;

import java.util.List;

import static com.sence.activity.CardAddActivity.BANK_CODE;

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
        StatusBarUtil.setLightMode(this);
        smartRefresh.setEnableRefresh(false);
        smartRefresh.setEnableLoadMore(false);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BankCardAdapter(R.layout.rv_item_bank_card);
        View footerView = LayoutInflater.from(this).inflate(R.layout.layout_footer_card, null);
        adapter.addFooterView(footerView);
        recycleView.setAdapter(adapter);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardActivity.this, CardAddActivity.class);
                startActivity(intent);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("card_id", adapter.getData().get(position).getId());
                setResult(BANK_CODE, intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BANK_CARD, new RUidListBean(LoginStatus.getUid(), page + "")).request(new ApiCallBack<List<PBankCardBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PBankCardBean> o, String msg) {
                if (page == 1) {
                    adapter.setNewData(o);
                } else {
                    adapter.addData(o);
                }
            }
        });
    }
}
