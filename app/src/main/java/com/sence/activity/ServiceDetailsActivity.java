package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.adapter.ServiceDetailsAdapter;
import com.sence.bean.request.RShopCommendBean;
import com.sence.bean.request.RShopDetailsBean;
import com.sence.bean.response.PServiceCommendBean;
import com.sence.bean.response.PServiceeDetails;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.StatusBarUtil;
import com.sence.view.NiceImageView;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceDetailsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ServiceDetailsAdapter mServiceDetailsAdapter;
    private ImageView mImg;
    private TextView mName,mAddress,mUserName;
    private NiceImageView mUserImg;
    private int page=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_servicedetails);
      StatusBarUtil.setLightMode(this);
      initData();
    }

    private void initData() {
        mRecyclerView = findViewById(R.id.recycle_servicedetails);
        mImg = findViewById(R.id.iv_img_servicedetails);
        mName = findViewById(R.id.tv_name_servicedetails);
        mAddress = findViewById(R.id.tv_address_servicedetails);
        mUserImg = findViewById(R.id.iv_userimg_servicedetails);
        mUserName = findViewById(R.id.tv_username_servicedetails);
        mServiceDetailsAdapter = new ServiceDetailsAdapter(ServiceDetailsActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ServiceDetailsActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mServiceDetailsAdapter);
        TextView mTitle = findViewById(R.id.pub_title);
        mTitle.setText("服务详情");
        ImageView mBack = findViewById(R.id.pub_back);
        ImageView mImg = findViewById(R.id.pub_right_img);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mImg.setImageResource(R.drawable.servicedetails_pingjia);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        doHttp();
    }

    private void doHttp() {
        HttpManager.getInstance().PlayNetCode(HttpCode.SERVE_DETAIL, new RShopDetailsBean("1", LoginStatus.getUid())).request(new ApiCallBack<PServiceeDetails>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(PServiceeDetails o, String msg) {
                Logger.e("msg==========" + msg);
                mName.setText(o.getTag());
                mAddress.setText(o.getPosition());
                mUserName.setText(o.getUsername());
                Glide.with(ServiceDetailsActivity.this)
                        .load(Urls.base_url + o.getImg())
                        .placeholder(R.drawable.hint_img)
                        .fallback(R.drawable.hint_img)
                        .into(mImg);
                Glide.with(ServiceDetailsActivity.this)
                        .load(Urls.base_url + o.getAvatar())
                        .placeholder(R.drawable.hint_img)
                        .fallback(R.drawable.hint_img)
                        .into(mUserImg);
            }
        });
        HttpManager.getInstance().PlayNetCode(HttpCode.SERVE_COMMENT_LIST, new RShopCommendBean("1",page+"","10")).request(new ApiCallBack<List<PServiceCommendBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PServiceCommendBean> o, String msg) {
                Logger.e("msg==========" + msg);
                if(o.size()>0){
                    mServiceDetailsAdapter.setList(o);
                }

            }
        });
    }
}
