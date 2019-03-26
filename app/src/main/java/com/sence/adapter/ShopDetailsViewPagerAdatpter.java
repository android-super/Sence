package com.sence.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ShopDetailsViewPagerAdatpter extends FragmentStatePagerAdapter {
    private Context context;
    private Fragment[] fragmentList;
    public ShopDetailsViewPagerAdatpter(FragmentManager fm, Context context, Fragment[] fragmentList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList[position];
    }

    @Override
    public int getCount() {
        return fragmentList.length;
    }
}