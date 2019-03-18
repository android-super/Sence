package com.sence.view;

import android.view.View;
import com.ToxicBakery.viewpager.transforms.ABaseTransformer;

/**
 * ViewPager渐变效果
 */
public class FadeTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        view.setAlpha(1 - Math.abs(position));
    }
}