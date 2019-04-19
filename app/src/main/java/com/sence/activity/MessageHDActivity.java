package com.sence.activity;

import android.os.Bundle;

import com.sence.R;
import com.sence.adapter.pager.ViewPagerAdapter;
import com.sence.base.BaseActivity;
import com.sence.fragment.MessageHDFragment;
import com.sence.fragment.MessagePrivateFragment;
import com.sence.utils.StatusBarUtil;
import com.sence.view.FadeTransformer;
import com.tlz.fucktablayout.FuckTabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * 互动消息
 */
public class MessageHDActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    FuckTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ViewPagerAdapter pagerAdapter;
    private String[] titles = {"私聊", " 赞 ", "评论"};
    private Fragment[] fragments;

    @Override
    public int onActLayout() {
        return R.layout.activity_message_hd;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        initTabLayout();
    }

    private void initTabLayout() {

        fragments = new Fragment[]{new MessagePrivateFragment(), new MessageHDFragment(), new MessageHDFragment()};
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
            pagerAdapter.addFragment(fragments[i], titles);
            Fragment myFragment = fragments[i];
            Bundle bundle = new Bundle();
            bundle.putInt("status", i);
            myFragment.setArguments(bundle);
        }

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setPageTransformer(false, new FadeTransformer());
//        tabLayout.getTabAt(0).getCustomView().setSelected(true);
    }
}
