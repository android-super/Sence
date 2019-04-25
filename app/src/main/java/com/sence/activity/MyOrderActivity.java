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
    private int pay;
    private int send;
    private int confirm;
    private int evlua;
    private MyOrderFragment allOrder;
    private MyOrderFragment waitPay;
    private MyOrderFragment waitDeliver;
    private MyOrderFragment waitTake;
    private MyOrderFragment waitEvaluate;
    private Fragment[] fragmentList;

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
        allOrder = new MyOrderFragment();
        waitPay = new MyOrderFragment();
        waitDeliver = new MyOrderFragment();
        waitTake = new MyOrderFragment();
        waitEvaluate = new MyOrderFragment();
        fragmentList = new Fragment[]{allOrder, waitPay, waitDeliver, waitTake, waitEvaluate};
        mMyOrderViewPagerAdatpter = new MyOrderViewPagerAdatpter(getSupportFragmentManager(), this, fragmentList,
                list, type);
        vpContentMyorder.setAdapter(mMyOrderViewPagerAdatpter);
        tlTitleMyorder.setupWithViewPager(vpContentMyorder);
        vpContentMyorder.setCurrentItem(type);
    }


    public void setTitleNum(String allNum, String waitPay, String waitSend, String waitConfirm, String waitEvlua) {
        pay = Integer.parseInt(waitPay);
        send = Integer.parseInt(waitSend);
        confirm = Integer.parseInt(waitConfirm);
        evlua = Integer.parseInt(waitEvlua);
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
        if (pay > 0) {
            tlTitleMyorder.addNumberBadge(i, size, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
        } else {
            tlTitleMyorder.removeBadge(i);
        }
    }

    public void setNum(String status) {
        if ("1".equals(status)) {
            if (pay > 1) {
                tlTitleMyorder.addNumberBadge(1, --pay, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
            } else {
                tlTitleMyorder.removeBadge(1);
            }
        } else if ("2".equals(status)) {
            if (send > 1) {

                tlTitleMyorder.addNumberBadge(1, --send, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
            } else {
                tlTitleMyorder.removeBadge(2);
            }
        } else if ("3".equals(status)) {
            if (confirm > 1) {
                tlTitleMyorder.addNumberBadge(1, --confirm, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
            } else {
                tlTitleMyorder.removeBadge(3);
            }
        } else if ("4".equals(status)) {
            if (evlua > 1) {

                tlTitleMyorder.addNumberBadge(1, --evlua, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
            } else {
                tlTitleMyorder.removeBadge(4);
            }
        }
    }

    public void refresh(String status) {
        switch (status){
            case "1":
                allOrder.reresh();
                waitPay.reresh();
                break;
            case "2":
                allOrder.reresh();
                waitDeliver.reresh();
                break;
            case "3":
                allOrder.reresh();
                waitTake.reresh();
                break;
            case "4":
                allOrder.reresh();
                waitEvaluate.reresh();
                break;
        }

    }
}