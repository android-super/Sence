package com.sence.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.google.android.material.tabs.TabLayout;
import com.sence.R;
import com.sence.adapter.pager.ViewPagerAdapter;
import com.sence.base.BaseActivity;
import com.sence.fragment.main.FocusFragment;
import com.sence.fragment.main.NoteFragment;
import com.sence.fragment.main.RecommendFragment;
import com.sence.view.FadeTransformer;

/**
 * 互动消息
 */
public class MessageHDActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ViewPagerAdapter pagerAdapter;
    private String[] titles = {"私聊", " 赞 ", "评论"};

    @Override
    public int onActLayout() {
        return R.layout.activity_message_hd;
    }

    @Override
    public void initView() {
        initTabLayout();
    }

    private void initTabLayout() {
        final Fragment[] fragments = {new FocusFragment(), new RecommendFragment(), new NoteFragment()};
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
            pagerAdapter.addFragment(fragments[i], titles);
        }
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setPageTransformer(false, new FadeTransformer());
        tabLayout.getTabAt(0).getCustomView().setSelected(true);
    }
}
