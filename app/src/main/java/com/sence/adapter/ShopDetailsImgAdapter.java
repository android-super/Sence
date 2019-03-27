package com.sence.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sence.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ShopDetailsImgAdapter extends PagerAdapter {
    private Context context;
    private List<String> list = new ArrayList<>();
    private ViewPager viewPager;
    public ShopDetailsImgAdapter(Context context, List<String> list, ViewPager viewPager){
        this.context =context ;
        this.list=list;
        this.viewPager=viewPager;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(list.get(position)).placeholder(R.drawable.hint_img).fallback(R.drawable.hint_img).into(imageView);
        viewPager.addView(imageView);
        return imageView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        viewPager.removeView((View) object);
    }
}
