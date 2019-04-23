package com.sence.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.sence.bean.request.tag.RTagInfoItem;
import com.sence.utils.Arith;

import java.util.ArrayList;

public class PictureShowTagLayout extends RelativeLayout {
    public void setTagInfoItems(ArrayList<RTagInfoItem> tagInfoItems, int width, int height) {
        if (tagInfoItems == null) {
            tagInfoItems = new ArrayList<>();
        }
        this.tagInfoItems = tagInfoItems;
        addItem(width, height, 0);
    }

    private ArrayList<RTagInfoItem> tagInfoItems;

    public PictureShowTagLayout(Context context) {
        super(context, null);
    }

    public PictureShowTagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void addItem(int width, int height, double tag_gap) {
        for (int i = 0; i < tagInfoItems.size(); i++) {
            View view = null;
            double width_scale = tagInfoItems.get(i).getWidth_scale();
            double height_scale = tagInfoItems.get(i).getHeight_scale();
            int direction = tagInfoItems.get(i).getDirection();
            LayoutParams params =
                    new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT);
            if (direction == 0) {
                params.leftMargin = (int) (width_scale * width);
                view = new PictureTagView(getContext(), PictureTagView.Direction.Left);
            } else {
                params.leftMargin = (int) (width_scale * width);
                view = new PictureTagView(getContext(), PictureTagView.Direction.Right);
            }
            ((PictureTagView) view).setContent(tagInfoItems.get(i).getContent());
            params.topMargin = (int) (height * height_scale);
            //上下位置在视图内
            if (params.topMargin < 0) {
                params.topMargin = 0;
            } else if ((params.topMargin + PictureTagView.getViewHeight()) > height) {
                params.topMargin = height - PictureTagView.getViewHeight();
            }
            view.setTag(i);
            this.addView(view, params);
        }
    }
}