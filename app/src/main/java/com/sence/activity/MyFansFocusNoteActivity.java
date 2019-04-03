package com.sence.activity;

import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sence.R;
import com.sence.adapter.MyFNAdapter;
import com.sence.adapter.pager.ViewPagerAdapter;
import com.sence.bean.request.RFansBean;
import com.sence.bean.request.RFocusBean;
import com.sence.fragment.my.MyFansFragment;
import com.sence.fragment.my.MyFocusFragment;
import com.sence.fragment.my.MyNoteFragment;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;

/**
 * 我的关注，粉丝，笔记
 */
public class MyFansFocusNoteActivity extends AppCompatActivity {
    private String titles[] = {"粉丝", "关注", "笔记"};


    private RecyclerView recycle_title;
    private LinearLayout search_layout;
    private ViewPager viewPager;

    private MyFNAdapter fnAdapter;
    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fans_focus_note);
        initView();
    }

    private void initView() {
        recycle_title = findViewById(R.id.recycle_title);
        search_layout = findViewById(R.id.search_layout);
        viewPager = findViewById(R.id.view_pager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recycle_title.setLayoutManager(linearLayoutManager);
        fnAdapter = new MyFNAdapter(R.layout.rv_item_fn_top);
        recycle_title.setAdapter(fnAdapter);

        fnAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                fnAdapter.setPosition(position);
                viewPager.setCurrentItem(position);
            }
        });
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new MyFansFragment(), titles);
        pagerAdapter.addFragment(new MyFocusFragment(), titles);
        pagerAdapter.addFragment(new MyNoteFragment(), titles);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fnAdapter.setPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }




}
