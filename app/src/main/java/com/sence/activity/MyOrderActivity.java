package com.sence.activity;

import android.content.Intent;
import android.graphics.Color;

import com.sence.R;
import com.sence.adapter.MyOrderViewPagerAdatpter;
import com.sence.base.BaseActivity;
import com.sence.fragment.MyOrderFragment;
import com.sence.utils.StatusBarUtil;
import com.tlz.fucktablayout.FuckTabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * 订单列表
 */
public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.tl_title_myorder)
    FuckTabLayout tlTitleMyorder;
    @BindView(R.id.vp_content_myorder)
    ViewPager vpContentMyorder;

    private String[] list = {"全部", "待支付", "待发货", "待收货", "待评价"};
    private MyOrderViewPagerAdatpter mMyOrderViewPagerAdatpter;
    private int type;

    @Override
    public int onActLayout() {
        return R.layout.activity_myorder;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
    }

    public void initData() {
        final Fragment[] fragmentList = {new MyOrderFragment(), new MyOrderFragment(), new MyOrderFragment(),
                new MyOrderFragment(), new MyOrderFragment()};
        mMyOrderViewPagerAdatpter = new MyOrderViewPagerAdatpter(getSupportFragmentManager(), this, fragmentList,
                list, type);
        vpContentMyorder.setAdapter(mMyOrderViewPagerAdatpter);
        tlTitleMyorder.setupWithViewPager(vpContentMyorder);

        vpContentMyorder.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        vpContentMyorder.setCurrentItem(type);
    }


    public void setTitleNum(String allNum, String waitPay, String waitSend, String waitConfirm, String waitEvlua) {
        int num = Integer.parseInt(allNum);
        int pay = Integer.parseInt(waitPay);
        int send = Integer.parseInt(waitSend);
        int confirm = Integer.parseInt(waitConfirm);
        int evlua = Integer.parseInt(waitEvlua);
        if (num > 0) {
            tlTitleMyorder.addNumberBadge(0, pay + send + confirm + evlua, Color.parseColor("#16a5af"),
                    Color.parseColor("#FFFFFF"), 30);
        }
        if (pay > 0) {
            tlTitleMyorder.addNumberBadge(1, pay, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
        if (send > 0) {
            tlTitleMyorder.addNumberBadge(2, send, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
        if (confirm > 0) {
            tlTitleMyorder.addNumberBadge(3, confirm, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
        if (evlua > 0) {
            tlTitleMyorder.addNumberBadge(4, evlua, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }
    }

    public void setTitlen(int i, int size) {
        if(size>0){
            tlTitleMyorder.addNumberBadge(i, size, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        }else{
            tlTitleMyorder.removeBadge(i);
        }
    }
}