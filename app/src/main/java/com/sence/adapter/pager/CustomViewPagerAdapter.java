package com.sence.adapter.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.sence.R;

/**
 * Created by zwy on 2019/3/15.
 * package_name is com.sence.adapter.pager
 * 描述:Sence
 */
public class CustomViewPagerAdapter extends ViewPagerAdapter {
    private Context context;
    private String[] titles = {"关注","笔记","推荐"};
    private int[] drawables = {R.drawable.ic_main_focus_selector,R.drawable.ic_main_note_selector,R.drawable.ic_main_recommend_selector};
    public CustomViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_view, null);
        ImageView iv_tab_view = view.findViewById(R.id.iv_tab_view);
        TextView tv_tab_view = view.findViewById(R.id.tv_tab_view);
        iv_tab_view.setImageResource(drawables[position]);
        tv_tab_view.setText(titles[position]);
        return view;
    }

}
