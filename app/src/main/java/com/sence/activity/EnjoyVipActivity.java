package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.EnjoyVipAdapter;
import com.sence.bean.request.RShopCommendBean;
import com.sence.bean.response.PEnjoyVipBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * 尊享会员
 */
public class EnjoyVipActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private EnjoyVipAdapter mEnjoyVipAdapter;
    private TextView mPrice;
    private int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enjoyvip);
        StatusBarUtil.setLightMode(this);
        initData();

    }

    private void initData() {
        TextView mTitle = findViewById(R.id.pub_title);
        mTitle.setText("尊享会员");
        ImageView mBack = findViewById(R.id.pub_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView = findViewById(R.id.recycle_enjoyvip);
        mPrice = findViewById(R.id.tv_price_enjoyvip);
        mPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnjoyVipActivity.this,UserDetailActivity.class));
            }
        });
        mEnjoyVipAdapter = new EnjoyVipAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mEnjoyVipAdapter);
        doHttp();
    }

    private void doHttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.USER_ENJOY_VIP, new RShopCommendBean(LoginStatus.getUid(),page+"","10")).request(new ApiCallBack<PEnjoyVipBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PEnjoyVipBean o, String msg) {
                Logger.e("msg==========" + msg);
                mPrice.setText("￥"+o.getMoney());
                if(o.getService().size()>0){
                    mEnjoyVipAdapter.setList(o.getService());
                }
            }
        });
    }
}
