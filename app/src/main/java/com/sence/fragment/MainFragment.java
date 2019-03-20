package com.sence.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.sence.R;
import com.sence.adapter.pager.CustomViewPagerAdapter;
import com.sence.fragment.main.FocusFragment;
import com.sence.fragment.main.NoteFragment;
import com.sence.fragment.main.RecommendFragment;
import com.sence.view.FadeTransformer;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tab_layout;
    private CustomViewPagerAdapter pagerAdapter;

    private String[] titles = {"关注", "笔记", "推荐"};

    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tab_layout = getView().findViewById(R.id.tab_layout);
        viewPager = getView().findViewById(R.id.view_pager);
        initTabLayout();
    }

    private void initTabLayout() {
        final Fragment[] fragments = {new FocusFragment(), new NoteFragment(), new RecommendFragment()};
        pagerAdapter = new CustomViewPagerAdapter(getChildFragmentManager(), getActivity());
        for (int i = 0; i < titles.length; i++) {
            tab_layout.addTab(tab_layout.newTab());
            pagerAdapter.addFragment(fragments[i], titles);
        }
        viewPager.setAdapter(pagerAdapter);
        tab_layout.setupWithViewPager(viewPager);
        viewPager.setPageTransformer(false, new FadeTransformer());
        for (int i = 0; i < tab_layout.getTabCount(); i++) {
            TabLayout.Tab tab = tab_layout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(pagerAdapter.getTabView(i));
            }
        }
        tab_layout.getTabAt(0).getCustomView().setSelected(true);
    }
}
