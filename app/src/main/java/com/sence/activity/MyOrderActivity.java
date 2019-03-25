package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sence.R;
import com.sence.adapter.MyOrderViewPagerAdatpter;
import com.sence.fragment.MyOrderFragment;
import com.sence.utils.StatusBarUtil;
import com.tlz.fucktablayout.FuckTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MyOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private FuckTabLayout tabLayout;
    private String[] list = {"全部", "待支付", "待发货", "待收货", "待评价"};
    private MyOrderViewPagerAdatpter pagerAdapter;
    private int[] nums= {1,0,0,2,1};
    private TextView tv_tab_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        StatusBarUtil.setLightMode(this);
        tabLayout = findViewById(R.id.tl_title_myorder);
        viewPager = findViewById(R.id.vp_content_myorder);
        findViewById(R.id.iv_back_myorder).setOnClickListener(this);
        final Fragment[] fragmentList = {new MyOrderFragment(), new MyOrderFragment(), new MyOrderFragment(), new MyOrderFragment(), new MyOrderFragment()};
        pagerAdapter = new MyOrderViewPagerAdatpter(getSupportFragmentManager(),this,fragmentList,list,nums);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        myOrderFragment1.result(new MyOrderFragment.MyCornerListener() {
//            @Override
//            public void add(int a, int b, int c, int d, int e) {
//                if(a>0){
//                    tabLayout.addNumberBadge(0, a,Color.parseColor("#16a5af"),Color.WHITE, 30);
//                }
//                if(b>0){
//                    tabLayout.addNumberBadge(0, b,Color.parseColor("#16a5af"),Color.WHITE, 30);
//                }
//                if(c>0){
//                    tabLayout.addNumberBadge(0, b,Color.parseColor("#16a5af"),Color.WHITE, 30);
//                }
//                if(d>0){
//                    tabLayout.addNumberBadge(0,d,Color.parseColor("#16a5af"),Color.WHITE, 30);
//                }
//                if(e>0){
//                    tabLayout.addNumberBadge(0, b,Color.parseColor("#16a5af"),Color.WHITE, 30);
//                }
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_myorder:
                finish();
                break;
        }
    }
}