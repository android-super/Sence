package com.sence.adapter.pager;

import android.os.Parcelable;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments = new ArrayList<>();

    private String[] pageTitles;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * @param fragment 添加Fragment
     */
    public void addFragment(Fragment fragment, String[] titles) {
        pageTitles = titles;
        if (!fragment.isAdded()) {
            mFragments.add(fragment);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}