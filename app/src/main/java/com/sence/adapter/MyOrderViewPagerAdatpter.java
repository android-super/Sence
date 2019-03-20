package com.sence.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sence.R;
import com.sence.view.BadgeView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyOrderViewPagerAdatpter extends FragmentPagerAdapter {
    private Context context;
    private Fragment[] fragmentList;
    private String[] list_Title;
    private int[] nums;
    public MyOrderViewPagerAdatpter(FragmentManager fm,Context context,Fragment[] fragmentList,String[] list_Title,int[] nums) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
        this.nums=nums;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment myFragment = fragmentList[position];
        Bundle bundle = new Bundle();
        bundle.putInt("status",position);
        myFragment.setArguments(bundle);
        return myFragment;
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

    /**根据角标获取标题item的布局文件*/
    public View getTabItemView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_title_layout, null);  // 标题布局
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(list_Title[position]);  // 设置标题内容

        View target = view.findViewById(R.id.badgeview_target);  // 右上角数字标记

        BadgeView badgeView = new BadgeView(context);
        badgeView.setTargetView(target);
        badgeView.setBadgeMargin(0, 6, 10, 0);
        badgeView.setTextSize(10);
        badgeView.setText(formatBadgeNumber(nums[position]));

        return view;
    }

    /**将int转化为String*/
    public static String formatBadgeNumber(int value) {
        if (value <= 0) {
            return null;
        }

        if (value < 100) {
            // equivalent to String#valueOf(int);
            return Integer.toString(value);
        }

        // my own policy
        return "99+";
    }
}