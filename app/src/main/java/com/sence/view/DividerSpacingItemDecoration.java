package com.sence.view;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zwy on 2019/3/20.
 * package_name is com.sence.view
 * 描述:SenceGit
 */
public class DividerSpacingItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private int orientation;
    private int space;
    private boolean isHeadView = false;

    public DividerSpacingItemDecoration(int orientation, int space) {
        this.orientation = orientation;
        this.space = space;
    }

    public DividerSpacingItemDecoration(int orientation, int space, boolean isHeadView) {
        this.orientation = orientation;
        this.space = space;
        this.isHeadView = isHeadView;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (orientation == HORIZONTAL) {
            if (position == 0) {
                outRect.left = space;
            }
            outRect.right = space;
        } else {
            if (isHeadView) {
                if (position != 0) {
                    outRect.bottom = space;
                }
            } else {
                outRect.bottom = space;
            }
        }

    }
}
