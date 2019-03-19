package com.sence.view;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class GridStagSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int spacing;

    public GridStagSpacingItemDecoration(int spanCount, int spacing) {
        this.spanCount = spanCount;
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position

        outRect.left = spacing / 2; // spacing - column * ((1f / spanCount) * spacing)
        outRect.right = spacing / 2; // (column + 1) * ((1f / spanCount) * spacing)

        if (position < spanCount) { // top edge
            outRect.top = spacing;
        }
        outRect.bottom = spacing; // item bottom
    }
}