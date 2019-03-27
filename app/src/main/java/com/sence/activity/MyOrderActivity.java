package com.sence.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.sence.R;
import com.sence.adapter.MyOrderViewPagerAdatpter;
import com.sence.fragment.MyOrderFragment;
import com.sence.utils.StatusBarUtil;
import com.tlz.fucktablayout.FuckTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MyOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private FuckTabLayout mFuckTabLayout;
    private String[] list = {"全部", "待支付", "待发货", "待收货", "待评价"};
    private MyOrderViewPagerAdatpter mMyOrderViewPagerAdatpter;
    private int[] nums= {1,0,0,2,1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        StatusBarUtil.setLightMode(this);
        mFuckTabLayout = findViewById(R.id.tl_title_myorder);
        mViewPager = findViewById(R.id.vp_content_myorder);
        findViewById(R.id.iv_back_myorder).setOnClickListener(this);
        final Fragment[] fragmentList = {new MyOrderFragment(), new MyOrderFragment(), new MyOrderFragment(), new MyOrderFragment(), new MyOrderFragment()};
        mMyOrderViewPagerAdatpter = new MyOrderViewPagerAdatpter(getSupportFragmentManager(),this,fragmentList,list,nums);
        mViewPager.setAdapter(mMyOrderViewPagerAdatpter);
        mFuckTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_myorder:
                finish();
                break;
        }
    }

    public void setTitleNum(String allNum, String waitPay, String waitSend, String waitConfirm, String waitEvlua) {
        int num = Integer.parseInt(allNum);
        int pay = Integer.parseInt(waitPay);
        int send = Integer.parseInt(waitSend);
        int confirm = Integer.parseInt(waitConfirm);
        int evlua = Integer.parseInt(waitEvlua);
        if(num>0){
            mFuckTabLayout.addNumberBadge(0, num, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
        if(pay>0){
            mFuckTabLayout.addNumberBadge(1, pay, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
        if(send>0){
            mFuckTabLayout.addNumberBadge(2, send, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
        if(confirm>0){
            mFuckTabLayout.addNumberBadge(3, confirm, Color.parseColor("#16a5af"), R.color.white, 25);
        }
        if(evlua>0){
            mFuckTabLayout.addNumberBadge(4, evlua, Color.parseColor("#16a5af"), R.color.white, 25);
        }
    }
}