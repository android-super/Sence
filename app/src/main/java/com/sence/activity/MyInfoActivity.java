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

    private ViewPager viewPager;
    private MyInfoRecommendViewPagerAdatpter myInfoRecommendAdapter;
    private String[] list = {"推荐","笔记","共享"};
    private RelativeLayout relativeLayout,footer;
    private AppBarLayout appBarLayout;
    private TabLayout tabLayoutbuttom;
    private TabLayout tabLayout;
    private Toolbar toolBar;
    private RelativeLayout title;
    private RelativeLayout imgBg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        relativeLayout = findViewById(R.id.rl_tool_myinfo);
        footer = findViewById(R.id.rl_footer_myinfo);
        tabLayout = findViewById(R.id.tl_tab_myinfo);
        appBarLayout = findViewById(R.id.al_appbar_myinfo);
        tabLayoutbuttom = findViewById(R.id.tl_tabhid_myinfo);
        imgBg = findViewById(R.id.iv_imgbg_myinfo);
        viewPager = findViewById(R.id.vp_content_myinfo);
        findViewById(R.id.iv_back_myinfo).setOnClickListener(this);
        toolBar = findViewById(R.id.tb_tab_myinfo);
        title = findViewById(R.id.rl_title_myinfo);
        final Fragment[] fragmentList = {new MyInfoRecommendFragment(), new MyInfoRecommendFragment(), new MyInfoRecommendFragment(), new MyOrderFragment(), new MyOrderFragment()};
        myInfoRecommendAdapter = new MyInfoRecommendViewPagerAdatpter(getSupportFragmentManager(),this,fragmentList,list);
        viewPager.setAdapter(myInfoRecommendAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayoutbuttom.setupWithViewPager(viewPager);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if( state == State.EXPANDED ) {

                    //展开状态
                    tabLayoutbuttom.setVisibility(View.VISIBLE);
                    toolBar.setVisibility(View.GONE);
                }else if(state == State.COLLAPSED){
                    tabLayoutbuttom.setVisibility(View.GONE);
                    toolBar.setVisibility(View.VISIBLE);
                    //折叠状态

                }else {
                    tabLayoutbuttom.setVisibility(View.VISIBLE);
                    toolBar.setVisibility(View.GONE);
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
