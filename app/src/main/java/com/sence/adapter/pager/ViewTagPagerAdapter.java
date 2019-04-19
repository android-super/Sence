package com.sence.adapter.pager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.sence.fragment.tag.TagFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewTagPagerAdapter extends FragmentStatePagerAdapter {
    public List<TagFragment> getmFragments() {
        return mFragments;
    }

    private List<TagFragment> mFragments = new ArrayList<>();

    private List<String> pageTitles = new ArrayList<>();

    public ViewTagPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * @param fragment 添加Fragment
     */
    public void addFragment(TagFragment fragment, String title) {
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
    public TagFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}