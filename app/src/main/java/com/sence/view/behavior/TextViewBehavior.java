package com.sence.view.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.blankj.utilcode.util.ConvertUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.orhanobut.logger.Logger;
import com.sence.view.NiceImageView;

/**
 * Created by zwy on 2019/3/21.
 * package_name is com.sence.view.behavior
 * 描述:SenceGit
 */
public class TextViewBehavior extends CoordinatorLayout.Behavior<TextView> implements AppBarLayout.OnOffsetChangedListener {
    private float translate = 0;
    private float scale;

    public TextViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull TextView child,
                                   @NonNull View dependency) {
        if (dependency instanceof AppBarLayout) {
            ((AppBarLayout) dependency).addOnOffsetChangedListener(this);
        }
        return true;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull TextView child,
                                          @NonNull View dependency) {

        child.setX(0);
        child.setY(dependency.getY());

        child.setScaleX(scale);
        child.setScaleY(scale);
        child.setTranslationY(translate);
        Logger.e("height===" + dependency.getHeight());

        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        Logger.e(i + "============" + appBarLayout.getTotalScrollRange());
        translate = (appBarLayout.getTotalScrollRange() - Math.abs(i)) + ConvertUtils.dp2px(70);
        scale = 1 - (float) Math.abs(i) / (2 * appBarLayout.getTotalScrollRange());
    }
}
