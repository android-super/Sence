package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sence.R;
import com.sence.adapter.MyFNAdapter;
import com.sence.adapter.pager.ViewPagerAdapter;
import com.sence.base.BaseActivity;
import com.sence.fragment.my.MyFansFragment;
import com.sence.fragment.my.MyFocusFragment;
import com.sence.fragment.my.MyNoteFragment;
import com.sence.utils.StatusBarUtil;

import java.util.Arrays;

/**
 * 我的关注，粉丝，笔记
 */
public class MyFansFocusNoteActivity extends BaseActivity {
    @BindView(R.id.recycle_title)
    RecyclerView recycleTitle;
    @BindView(R.id.search_layout)
    LinearLayout searchLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private String titles[] = {"粉丝", "关注", "笔记"};

    private MyFNAdapter fnAdapter;
    private ViewPagerAdapter pagerAdapter;

    private int position;


    @Override
    public int onActLayout() {
        return R.layout.activity_my_fans_focus_note;
    }

    public void initView() {
        StatusBarUtil.setLightMode(this);
        position = getIntent().getIntExtra("position", 0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recycleTitle.setLayoutManager(linearLayoutManager);
        fnAdapter = new MyFNAdapter(R.layout.rv_item_fn_top);
        recycleTitle.setAdapter(fnAdapter);
        fnAdapter.setNewData(Arrays.asList(titles));
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
        viewPager.setCurrentItem(position);
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyFansFocusNoteActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
