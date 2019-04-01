package com.sence.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyOrderViewPagerAdatpter extends FragmentPagerAdapter {
    private Context context;
    private Fragment[] fragmentList;
    private String[] list_Title;
    private int postion;

    public MyOrderViewPagerAdatpter(FragmentManager fm, Context context, Fragment[] fragmentList, String[] list_Title, int postion) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
        this.postion = postion;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment myFragment = fragmentList[position];
        Bundle bundle = new Bundle();
        bundle.putInt("status", position);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    public int getCount() {
        return list_Title.length;
    }

    /**
     * //此方法用来显示tab上的名字
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title[position];
    }


}