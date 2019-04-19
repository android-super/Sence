package com.sence.activity;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sence.R;
import com.sence.adapter.CardAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.RKeywordBean;
import com.sence.bean.response.PBankCardBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import static com.sence.activity.CardAddActivity.BANK_CODE;
/**
 * 选择银行卡
 */

public class CardListActivity extends BaseActivity {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private CardAdapter adapter;
    private String keyword = "";

    private String bank_id;
    private String bank_name;

    @Override
    public int onActLayout() {
        return R.layout.activity_card_list;
    }

    @Override
    public void initView() {
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CardAdapter(R.layout.rv_item_card);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                bank_id = adapter.getData().get(position).getId();
                bank_name =adapter.getData().get(position).getBank_name();
                Intent intent = new Intent();
                intent.putExtra("bank_id", bank_id);
                intent.putExtra("bank_name",bank_name);
                setResult(BANK_CODE, intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.BANK_LIST, new RKeywordBean(keyword)).request(new ApiCallBack<List<PBankCardBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PBankCardBean> o, String msg) {
                adapter.setNewData(o);
            }
        });
    }
}
