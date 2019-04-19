package com.sence.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.sence.bean.request.tag.RTagInfo;
import com.sence.bean.request.tag.RTagInfoItem;
import com.sence.utils.Arith;

import java.util.ArrayList;
import java.util.List;

public class PictureTagLayout extends RelativeLayout implements View.OnTouchListener {
    private static final int CLICKRANGE = 5;
    int startX = 0;
    int startY = 0;
    int startTouchViewLeft = 0;
    int startTouchViewTop = 0;
    private View touchView, clickView;
    private EditListener listener;

    private int tag = -1;

    public ArrayList<RTagInfoItem> getTagInfoItems() {
        return tagInfoItems;
    }

    public void setTagInfoItems(ArrayList<RTagInfoItem> tagInfoItems) {
        if (tagInfoItems == null) {
            tagInfoItems = new ArrayList<>();
        }
        this.tagInfoItems = tagInfoItems;
        if (tagInfoItems == null || tagInfoItems.size() == 0) {
            addItem(200, 300);
        }
    }

    public void setTagInfoItems(ArrayList<RTagInfoItem> tagInfoItems, int width, int height) {
        if (tagInfoItems == null) {
            tagInfoItems = new ArrayList<>();
        }
        this.tagInfoItems = tagInfoItems;
        addItem(width, height, 0);
    }

    private ArrayList<RTagInfoItem> tagInfoItems;

    public interface EditListener {
        void onEditChanged(boolean isEdit);
    }

    public void addOnEditListener(EditListener listener) {
        this.listener = listener;
    }

    public PictureTagLayout(Context context) {
        super(context, null);
    }

    public PictureTagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchView = null;
                if (clickView != null) {
                    ((PictureTagView) clickView).setStatus(PictureTagView.Status.Normal);
                    if (listener != null) {
                        listener.onEditChanged(false);
                    }
                    clickView = null;
                }
                startX = (int) event.getX();
                startY = (int) event.getY();
                if (hasView(startX, startY)) {
                    startTouchViewLeft = touchView.getLeft();
                    startTouchViewTop = touchView.getTop();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                moveView((int) event.getX(),
                        (int) event.getY());
                break;
            case MotionEvent.ACTION_UP:
                int endX = (int) event.getX();
                int endY = (int) event.getY();

                if (!hasView(endX, endY) && Math.abs(endX - startX) < CLICKRANGE && Math.abs(endY - startY) < CLICKRANGE) {
                    addItem(endX, endY);
                }
                //如果挪动的范围很小，则判定为单击
                if (touchView != null && Math.abs(endX - startX) < CLICKRANGE && Math.abs(endY - startY) < CLICKRANGE) {
                    //当前点击的view进入编辑状态
                    ((PictureTagView) touchView).setStatus(PictureTagView.Status.Edit);
                    if (listener != null) {
                        listener.onEditChanged(true);
                    }
                    clickView = touchView;
                }
                touchView = null;
                break;
        }
        return true;
    }

    /**
     * 设置内容
     *
     * @param content
     */
    public void setContent(String content) {
        if (TextUtils.isEmpty(content) || clickView == null) {
            return;
        }
        ((PictureTagView) clickView).setContent(content);
        tagInfoItems.get((Integer) clickView.getTag()).setContent(content);
    }


    private void addItem(int x, int y) {
        tag++;
        View view = null;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        if (x > getWidth() * 0.5) {
            params.leftMargin = x - PictureTagView.getViewWidth();
            view = new PictureTagView(getContext(), PictureTagView.Direction.Right);
        } else {
            params.leftMargin = x;
            view = new PictureTagView(getContext(), PictureTagView.Direction.Left);
        }
        params.topMargin = y;
        view.setTag(tag);
        //上下位置在视图内
        if (params.topMargin < 0) params.topMargin = 0;
        else if ((params.topMargin + PictureTagView.getViewHeight()) > getHeight())
            params.topMargin = getHeight() - PictureTagView.getViewHeight();
        this.addView(view, params);
        double width_scale = Arith.div(params.leftMargin, getWidth(), 4);
        double height_scale = Arith.div(params.topMargin, getHeight(), 4);
        RTagInfoItem rTagInfoItem = new RTagInfoItem(width_scale, height_scale);
        tagInfoItems.add(rTagInfoItem);
    }

    private void addItem(int width, int height, double tag_gap) {
        for (int i = 0; i < tagInfoItems.size(); i++) {
            tag++;
            View view = null;
            double width_scale = tagInfoItems.get(i).getWidth_scale();
            double height_scale = tagInfoItems.get(i).getHeight_scale();
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            if (width_scale >= 0.52) {
                params.leftMargin = (int) (width_scale * width);
                view = new PictureTagView(getContext(), PictureTagView.Direction.Right);
            } else if (width_scale <= 0.48) {
                params.leftMargin = (int) ((width_scale * width));
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

    private void moveView(int x, int y) {
        if (touchView == null) return;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.leftMargin = x - startX + startTouchViewLeft;
        params.topMargin = y - startY + startTouchViewTop;
        //限制子控件移动必须在视图范围内
        if (params.leftMargin < 0 || (params.leftMargin + touchView.getWidth()) > getWidth())
            params.leftMargin = touchView.getLeft();
        if (params.topMargin < 0 || (params.topMargin + touchView.getHeight()) > getHeight())
            params.topMargin = touchView.getTop();
        touchView.setLayoutParams(params);
        double width_scale = Arith.div(params.leftMargin, getWidth(), 4);
        double height_scale = Arith.div(params.topMargin, getHeight(), 4);
        tagInfoItems.get((Integer) touchView.getTag()).setWidth_scale(width_scale);
        tagInfoItems.get((Integer) touchView.getTag()).setHeight_scale(height_scale);
    }

    private boolean hasView(int x, int y) {
        //循环获取子view，判断xy是否在子view上，即判断是否按住了子view
        for (int index = 0; index < this.getChildCount(); index++) {
            View view = this.getChildAt(index);
            int left = (int) view.getX();
            int top = (int) view.getY();
            int right = view.getRight();
            int bottom = view.getBottom();
            Rect rect = new Rect(left, top, right, bottom);
            boolean contains = rect.contains(x, y);
            //如果是与子view重叠则返回真,表示已经有了view不需要添加新view了
            if (contains) {
                touchView = view;
                touchView.bringToFront();
                return true;
            }
        }
        touchView = null;
        return false;
    }
}