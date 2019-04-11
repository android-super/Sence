package com.sence.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyInfoRecommendViewPagerAdatpter extends FragmentPagerAdapter {
    private Context context;
    private Fragment[] fragmentList = null;
    private String[] list_Title = null;
    public MyInfoRecommendViewPagerAdatpter(FragmentManager fm, Context context,Fragment[] fragmentList, String[] list_Title) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;

    }
    public void setList(Fragment[] fragmentList, String[] list_Title){
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragments = fragmentList[position];
        Bundle bundle = new Bundle();
        bundle.putString("type",position+1  +"");
        fragments.setArguments(bundle);
        return fragments;
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