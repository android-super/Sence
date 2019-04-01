package com.sence.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.adapter.MyOrderViewPagerAdatpter;
import com.sence.fragment.MyOrderFragment;
import com.sence.utils.StatusBarUtil;
import com.tlz.fucktablayout.FuckTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MyOrderActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private FuckTabLayout mFuckTabLayout;
    private String[] list = {"全部", "待支付", "待发货", "待收货", "待评价"};
    private MyOrderViewPagerAdatpter mMyOrderViewPagerAdatpter;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        initData();

    }

    private void initData() {
        mFuckTabLayout = findViewById(R.id.tl_title_myorder);
        mViewPager = findViewById(R.id.vp_content_myorder);
        TextView mTitle = findViewById(R.id.pub_title);
        mTitle.setText("我的订单");
        ImageView mBack = findViewById(R.id.pub_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final Fragment[] fragmentList = {new MyOrderFragment(), new MyOrderFragment(), new MyOrderFragment(), new MyOrderFragment(), new MyOrderFragment()};
        mMyOrderViewPagerAdatpter = new MyOrderViewPagerAdatpter(getSupportFragmentManager(),this,fragmentList,list,type);
        mViewPager.setAdapter(mMyOrderViewPagerAdatpter);
        mFuckTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(type);
    }


    public void setTitleNum(String allNum, String waitPay, String waitSend, String waitConfirm, String waitEvlua) {
        int num = Integer.parseInt(allNum);
        int pay = Integer.parseInt(waitPay);
        int send = Integer.parseInt(waitSend);
        int confirm = Integer.parseInt(waitConfirm);
        int evlua = Integer.parseInt(waitEvlua);
        if(num>0){
            mFuckTabLayout.addNumberBadge(0, pay+send+confirm+evlua, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
        if(pay>0){
            mFuckTabLayout.addNumberBadge(1, pay, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
        if(send>0){
            mFuckTabLayout.addNumberBadge(2, send, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
        if(confirm>0){
            mFuckTabLayout.addNumberBadge(3, confirm, Color.parseColor("#16a5af"),Color.parseColor("#FFFFFF"), 30);
        }
        if(evlua>0){
            mFuckTabLayout.addNumberBadge(4, evlua, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
    }
}