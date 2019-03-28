package com.sence.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.sence.R;
import com.sence.adapter.MyInfoRecommendViewPagerAdatpter;
import com.sence.fragment.MyInfoRecommendFragment;
import com.sence.fragment.MyOrderFragment;
import com.sence.utils.NavigationBarUtil;
import com.sence.utils.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private MyInfoRecommendViewPagerAdatpter mMyInfoRecommendViewPagerAdatpter;
    private String[] list = {"推荐","笔记","共享"};
    private AppBarLayout mAppBarLayout;
    private TabLayout mTabLayoutButtom;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private RelativeLayout mTitle;
    private RelativeLayout mImgBg;
    private ImageView mBack;
    private View mView;
    private TextView mDeditor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        StatusBarUtil.setLightMode(this);
        if(NavigationBarUtil.hasNavigationBar(this)){
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
        StatusBarUtil.setLightMode(this);
        mTabLayout = findViewById(R.id.tl_tab_myinfo);
        mAppBarLayout = findViewById(R.id.al_appbar_myinfo);
        mDeditor = findViewById(R.id.tv_deditor_myinfo);
        mTabLayoutButtom = findViewById(R.id.tl_tabhid_myinfo);
        mImgBg = findViewById(R.id.iv_imgbg_myinfo);
        mView = findViewById(R.id.shop_view_myinfo);
        mViewPager = findViewById(R.id.vp_content_myinfo);
        mBack = findViewById(R.id.iv_back_myinfo);
        mBack.setOnClickListener(this);
        mToolbar = findViewById(R.id.tb_tab_myinfo);
        final Fragment[] fragmentList = {new MyInfoRecommendFragment(), new MyInfoRecommendFragment(), new MyInfoRecommendFragment(), new MyOrderFragment(), new MyOrderFragment()};
        mMyInfoRecommendViewPagerAdatpter = new MyInfoRecommendViewPagerAdatpter(getSupportFragmentManager(),this,fragmentList,list);
        mViewPager.setAdapter(mMyInfoRecommendViewPagerAdatpter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayoutButtom.setupWithViewPager(mViewPager);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                mView.setAlpha(alpha);
                mTabLayout.setAlpha(alpha);
                mTabLayoutButtom.setAlpha(1-alpha);
                Scale(mTabLayoutButtom,1-alpha);
                if(alpha>=1){
                    mTabLayout.setVisibility(View.VISIBLE);
                }else{
                    mTabLayout.setVisibility(View.GONE);
                }
                if (alpha > 0.8) {
                    mBack.setImageResource(R.drawable.login_fanhui);
                    mDeditor.setTextColor(Color.parseColor("#222222"));
                } else {
                    mBack.setImageResource(R.drawable.myinfo_back);
                    mDeditor.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });
    }

    public void Scale(View view,float scaleY){
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Math.round(ConvertUtils.dp2px(100)*scaleY)));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_myinfo:
                finish();
                break;
        }
    }

}
