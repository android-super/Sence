package com.sence.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.google.android.material.tabs.TabLayout;
import com.sence.R;
import com.sence.adapter.MyOrderViewPagerAdatpter;
import com.sence.fragment.MyOrderFragment;
import com.sence.utils.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MyOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String[] list = {"全部", "待支付", "待发货", "待收货", "待评价"};
    private MyOrderViewPagerAdatpter pagerAdapter;
    private int[] nums= {1,0,0,2,1};
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
        viewPager.setOffscreenPageLimit(5);
//        setUpTabBadge();


    }


    private void setUpTabBadge() {
        for (int i = 0; i < list.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);

            // 更新Badge前,先remove原来的customView,否则Badge无法更新
            View customView = tab.getCustomView();
            if (customView != null) {
                ViewParent parent = customView.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(customView);
                }
            }

            // 更新CustomView
            tab.setCustomView(pagerAdapter.getTabItemView(i));
        }

        // 需加上以下代码,不然会出现更新Tab角标后,选中的Tab字体颜色不是选中状态的颜色
        tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getCustomView().setSelected(true);
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