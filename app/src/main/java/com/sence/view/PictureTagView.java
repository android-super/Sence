package com.sence.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sence.R;

public class PictureTagView extends RelativeLayout {

    private Context context;
    private TextView tvPictureTagLabel;
    private ImageView tag_img;
    private View loTag;

    public enum Status {Normal, Edit}

    public enum Direction {Left, Right}

    public Direction getDirection() {
        return direction;
    }

    private Direction direction;
    private static final int ViewWidth = 80;
    private static final int ViewHeight = 50;

    public PictureTagView(Context context, Direction direction) {
        super(context);
        this.context = context;
        this.direction = direction;
        initViews();
        init();
    }

    /**
     * 初始化视图
     **/
    protected void initViews() {
        LayoutInflater.from(context).inflate(R.layout.picturetagview, this, true);
        tvPictureTagLabel = (TextView) findViewById(R.id.tvPictureTagLabel);
        tag_img = (ImageView) findViewById(R.id.tag_img);
        loTag = findViewById(R.id.loTag);
    }

    /**
     * 初始化
     **/
    protected void init() {
        directionChange();
    }


    public void setStatus(Status status) {
        switch (status) {
            case Normal:
                tvPictureTagLabel.setVisibility(View.VISIBLE);
                tag_img.setVisibility(View.VISIBLE);
                break;
            case Edit:
                tvPictureTagLabel.setVisibility(View.VISIBLE);
                tag_img.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setContent(String content) {
        if (TextUtils.isEmpty(content)) {
            tvPictureTagLabel.setVisibility(View.GONE);
            tag_img.setVisibility(View.VISIBLE);
            return;
        }
        tvPictureTagLabel.setText(content);
        tag_img.setVisibility(GONE);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View parent = (View) getParent();
        int halfParentW = (int) (parent.getWidth() * 0.5);
        int center = (int) ((l + (this.getWidth() * 0.5)));
        if (center <= halfParentW) {
            direction = Direction.Left;
        } else {
            direction = Direction.Right;
        }
        directionChange();
    }

    private void directionChange() {
        switch (direction) {
            case Left:
                loTag.setBackgroundResource(R.drawable.note_tag_zuo);
                break;
            case Right:
                loTag.setBackgroundResource(R.drawable.note_tag_you);
                break;
        }
    }

    public static int getViewWidth() {
        return ViewWidth;
    }

    public static int getViewHeight() {
        return ViewHeight;
    }
}