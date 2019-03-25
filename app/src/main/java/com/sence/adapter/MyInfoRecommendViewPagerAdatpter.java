package com.sence.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyInfoRecommendViewPagerAdatpter extends FragmentPagerAdapter {
    private Context context;
    private Fragment[] fragmentList;
    private String[] list_Title;
    public MyInfoRecommendViewPagerAdatpter(FragmentManager fm, Context context, Fragment[] fragmentList, String[] list_Title) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList[position];
    }
    @Override
    public int getCount() {
        return list_Title.length;
    }
    /**
     * //此方法用来显示tab上的名字
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title[position];
    }


}