package com.sence.view;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import com.ToxicBakery.viewpager.transforms.ABaseTransformer;

/**
 * ViewPager渐变效果
 */
public class FadeTransformer implements ViewPager.PageTransformer {


    @Override
    public void transformPage(@NonNull View page, float position) {
        page.setAlpha(1 - Math.abs(position));
    }
}