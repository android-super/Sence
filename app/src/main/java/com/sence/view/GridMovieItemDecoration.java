package com.sence.view;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;


public class GridMovieItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
//    private List<MovieMultipleItem> list;

//    public GridMovieItemDecoration(int spanCount, int spacing, List<MovieMultipleItem> list) {
//        this.spanCount = spanCount;
//        this.spacing = spacing;
//        this.list = list;
//    }
//
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        int position = parent.getChildAdapterPosition(view); // item position
//        if (list == null || list.size() == 0) {
//            return;
//        }
//        if (position == 0) {
//            outRect.left = 0;
//            outRect.right = 0;
//        } else {
//            if (list.get(position - 1).getItemType() == MovieMultipleItem.HEAD) {
//                outRect.left = 0;
//                outRect.right = 0;
//            } else {
//                outRect.bottom = DensityUtil.dip2px(parent.getContext(), 2);
//                if (list.get(position - 1).getDirection_type() == MovieMultipleItem.left) {
//                    outRect.left = 0; // column * ((1f / spanCount) * spacing)
//                    outRect.right = DensityUtil.dip2px(parent.getContext(), 1f); // spacing - (column + 1) * ((1f /    spanCount) * spacing)
//                } else {
//                    outRect.left = DensityUtil.dip2px(parent.getContext(), 1f); // column * ((1f / spanCount) * spacing)
//                    outRect.right = 0;
//                }
//            }
//        }
//    }
}