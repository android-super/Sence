package com.sence.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.sence.R;
import com.sence.adapter.MyInfoRecommendViewPagerAdatpter;
import com.sence.fragment.MyInfoRecommendFragment;
import com.sence.fragment.MyOrderFragment;
import com.sence.view.AppBarStateChangeListener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private MyInfoRecommendViewPagerAdatpter mMyInfoRecommendViewPagerAdatpter;
    private String[] list = {"推荐","笔记","共享"};
    private RelativeLayout mRelativeLayout,mFlooter;
    private AppBarLayout mAppBarLayout;
    private TabLayout mTabLayoutButtom;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private RelativeLayout mTitle;
    private RelativeLayout mImgBg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        mRelativeLayout = findViewById(R.id.rl_tool_myinfo);
        mFlooter = findViewById(R.id.rl_footer_myinfo);
        mTabLayout = findViewById(R.id.tl_tab_myinfo);
        mAppBarLayout = findViewById(R.id.al_appbar_myinfo);
        mTabLayoutButtom = findViewById(R.id.tl_tabhid_myinfo);
        mImgBg = findViewById(R.id.iv_imgbg_myinfo);
        mViewPager = findViewById(R.id.vp_content_myinfo);
        findViewById(R.id.iv_back_myinfo).setOnClickListener(this);
        mToolbar = findViewById(R.id.tb_tab_myinfo);
        mTitle = findViewById(R.id.rl_title_myinfo);
        final Fragment[] fragmentList = {new MyInfoRecommendFragment(), new MyInfoRecommendFragment(), new MyInfoRecommendFragment(), new MyOrderFragment(), new MyOrderFragment()};
        mMyInfoRecommendViewPagerAdatpter = new MyInfoRecommendViewPagerAdatpter(getSupportFragmentManager(),this,fragmentList,list);
        mViewPager.setAdapter(mMyInfoRecommendViewPagerAdatpter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayoutButtom.setupWithViewPager(mViewPager);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if( state == State.EXPANDED ) {

                    //展开状态
                    mTabLayoutButtom.setVisibility(View.VISIBLE);
                    mToolbar.setVisibility(View.GONE);
                }else if(state == State.COLLAPSED){
                    mTabLayoutButtom.setVisibility(View.GONE);
                    mToolbar.setVisibility(View.VISIBLE);
                    //折叠状态

                }else {
                    mTabLayoutButtom.setVisibility(View.VISIBLE);
                    mToolbar.setVisibility(View.GONE);
                    //中间状态

                }
            }
        });
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
