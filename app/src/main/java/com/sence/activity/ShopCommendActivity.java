package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.ShopCommendAdapter;
import com.sence.bean.request.RShopCommentBean;
import com.sence.bean.response.PShopCommendBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCommendActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ShopCommendAdapter mShopCommendAdapter;
    private int page=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcommend);
        StatusBarUtil.setLightMode(this);
        initData();
    }
    private void initData() {
        mRecyclerView = findViewById(R.id.recycle_shopcommend);
        mShopCommendAdapter = new ShopCommendAdapter(ShopCommendActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopCommendActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mShopCommendAdapter);
        doHttp();
        TextView mTitle = findViewById(R.id.pub_title);
        mTitle.setText("小伙伴们说");
        ImageView mBack = findViewById(R.id.pub_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void doHttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.COMMENT_SHOP_LIST, new RShopCommentBean("1",page+"","10", LoginStatus.getUid())).request(new ApiCallBack<List<PShopCommendBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PShopCommendBean> o, String msg) {
                Logger.e("msg==========" + msg);
                if(o.size()>0){
                    mShopCommendAdapter.setList(o);
                }

            }
        });
    }
}
