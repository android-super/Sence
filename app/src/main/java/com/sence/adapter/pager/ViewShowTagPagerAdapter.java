package com.sence.adapter.pager;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.sence.fragment.tag.ShowTagFragment;
import com.sence.fragment.tag.TagFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewShowTagPagerAdapter extends FragmentStatePagerAdapter {
    public List<ShowTagFragment> getmFragments() {
        return mFragments;
    }

    private List<ShowTagFragment> mFragments = new ArrayList<>();

    private List<String> pageTitles = new ArrayList<>();

    public ViewShowTagPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * @param fragment 添加Fragment
     */
    public void addFragment(ShowTagFragment fragment, String title) {
        if (!fragment.isAdded()) {
            mFragments.add(fragment);
            pageTitles.add(title);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles.get(position);
    }

    @Override
    public ShowTagFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}