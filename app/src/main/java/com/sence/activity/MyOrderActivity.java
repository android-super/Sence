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
    private int num;
    private int pay;
    private int send;
    private int confirm;
    private int evlua;

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
        vpContentMyorder.setCurrentItem(type);
    }


    public void setTitleNum(String allNum, String waitPay, String waitSend, String waitConfirm, String waitEvlua) {
        num = Integer.parseInt(allNum);
        pay = Integer.parseInt(waitPay);
        send = Integer.parseInt(waitSend);
        confirm = Integer.parseInt(waitConfirm);
        evlua = Integer.parseInt(waitEvlua);
        if (num > 0) {
            tlTitleMyorder.addNumberBadge(0, num, Color.parseColor("#16a5af"),
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
                if (num > 1) {
                    tlTitleMyorder.addNumberBadge(0, --num, Color.parseColor("#16a5af"),
                            Color.parseColor("#FFFFFF"), 30);
                } else {
                    tlTitleMyorder.removeBadge(0);
                }
                tlTitleMyorder.removeBadge(1);
            }
        } else if ("2".equals(status)) {
            if (send > 1) {

                tlTitleMyorder.addNumberBadge(1, --send, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
            } else {
                if (num > 1) {
                    tlTitleMyorder.addNumberBadge(0, --num, Color.parseColor("#16a5af"),
                            Color.parseColor("#FFFFFF"), 30);
                } else {
                    tlTitleMyorder.removeBadge(0);
                }
                tlTitleMyorder.removeBadge(2);
            }
        } else if ("3".equals(status)) {
            if (confirm > 1) {
                tlTitleMyorder.addNumberBadge(1, --confirm, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
            } else {
                if (num > 1) {
                    tlTitleMyorder.addNumberBadge(0, --num, Color.parseColor("#16a5af"),
                            Color.parseColor("#FFFFFF"), 30);
                } else {
                    tlTitleMyorder.removeBadge(0);
                }
                tlTitleMyorder.removeBadge(3);
            }
        } else if ("4".equals(status)) {
            if (evlua > 1) {

                tlTitleMyorder.addNumberBadge(1, --evlua, Color.parseColor("#16a5af"), Color.parseColor("#FFFFFF"), 30);
            } else {
                if (num > 1) {
                    tlTitleMyorder.addNumberBadge(0, --num, Color.parseColor("#16a5af"),
                            Color.parseColor("#FFFFFF"), 30);
                } else {
                    tlTitleMyorder.removeBadge(0);
                }
                tlTitleMyorder.removeBadge(4);
            }
        } else if ("5".equals(status) || "6".equals(status) || "7".equals(status) || "8".equals(status)) {
            if (num > 1) {
                tlTitleMyorder.addNumberBadge(0, --num, Color.parseColor("#16a5af"),
                        Color.parseColor("#FFFFFF"), 30);
            } else {
                tlTitleMyorder.removeBadge(0);
            }
        }
    }
}