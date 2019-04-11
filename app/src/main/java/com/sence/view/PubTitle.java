package com.sence.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.sence.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

/**
 * Created by zwy on 2019/4/3.
 * package_name is com.sence.view
 * 描述:SenceGit
 */
public class PubTitle extends Toolbar {
    private Context context;

    private ImageView back_img;
    private ImageView right_img;
    private TextView right_text;

    private boolean is_back = true;
    private boolean is_animator_back = false;
    private int tool_cover_background = 0x00ffffff;
    private String tool_cover_title;
    private Drawable tool_cover_right_img;
    private String tool_cover_right_text;
    private int tool_cover_right_color = 0xffffff;
    private int tool_cover_right_size = 14;

    public PubTitle(Context context) {
        this(context, null);
    }

    public PubTitle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PubTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PubTitle);
        is_back = array.getBoolean(R.styleable.PubTitle_is_back, is_back);
        is_animator_back = array.getBoolean(R.styleable.PubTitle_is_animator_back, is_animator_back);
        tool_cover_background = array.getColor(R.styleable.PubTitle_tool_cover_background, tool_cover_background);
        tool_cover_title = array.getString(R.styleable.PubTitle_tool_cover_title);
        tool_cover_right_img = array.getDrawable(R.styleable.PubTitle_tool_cover_right_img);
        tool_cover_right_text = array.getString(R.styleable.PubTitle_tool_cover_right_text);
        tool_cover_right_color = array.getColor(R.styleable.PubTitle_tool_cover_right_color,
                tool_cover_right_color);
        tool_cover_right_size = array.getDimensionPixelSize(R.styleable.PubTitle_tool_cover_right_size,
                tool_cover_right_size);
        array.recycle();
        setBackgroundColor(tool_cover_background);
        addRelativeLayout();
    }

    private void addRelativeLayout() {
        RelativeLayout parent = new RelativeLayout(context);
        Toolbar.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        parent.setLayoutParams(layoutParams);
        addView(parent);
        addBack(parent);
        addTitle(parent);
        addRightImage(parent);
        addRightText(parent);
    }

    private void addRightText(RelativeLayout parent) {
        if (tool_cover_right_text == null) {
            return;
        }
        right_text = new TextView(context);
        RelativeLayout.LayoutParams layoutParams;
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ConvertUtils.dp2px(50));
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.rightMargin = ConvertUtils.dp2px(15);
        right_text.setGravity(Gravity.CENTER);
        right_text.setLayoutParams(layoutParams);
        right_text.getPaint().setTextSize(tool_cover_right_size);
        right_text.setTextColor(tool_cover_right_color);
        right_text.setText(tool_cover_right_text);
        parent.addView(right_text);
    }

    public  void setRigthText(String content){
        if(right_text!=null) {
            right_text.setText(content);
            invalidate();
        }
    }

    private void addRightImage(RelativeLayout parent) {
        if (tool_cover_right_img == null) {
            return;
        }
        right_img = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ConvertUtils.dp2px(50),
                ConvertUtils.dp2px(50));
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        right_img.setLayoutParams(layoutParams);
        right_img.setImageDrawable(tool_cover_right_img);
        right_img.setScaleType(ImageView.ScaleType.CENTER);
        parent.addView(right_img);

    }
    public void setRightImg(int img){
        if(right_img!=null) {
            right_img.setImageResource(img);
            invalidate();
        }
    }

    private void addBack(RelativeLayout parent) {
        back_img = new ImageView(context);
        back_img.setLayoutParams(new RelativeLayout.LayoutParams(ConvertUtils.dp2px(50), ConvertUtils.dp2px(50)));
        back_img.setImageResource(R.drawable.fanhui);
        back_img.setScaleType(ImageView.ScaleType.CENTER);
        parent.addView(back_img);
        if (is_animator_back) {
            back_img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.finishAfterTransition((Activity) context);
                }
            });
        } else if (is_back) {
            back_img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                }
            });
        } else {
            back_img.setVisibility(GONE);
        }
    }

    private void addTitle(RelativeLayout parent) {
        if (TextUtils.isEmpty(tool_cover_title)) {
            return;
        }
        TextView title = new TextView(context);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.leftMargin = ConvertUtils.dp2px(80);
        layoutParams.rightMargin = ConvertUtils.dp2px(80);
        title.setGravity(Gravity.CENTER);
        title.setEllipsize(TextUtils.TruncateAt.END);
        title.setLayoutParams(layoutParams);
        title.setTextSize(16);
        title.setTextColor(Color.parseColor("#000000"));
        title.setText(tool_cover_title);
        parent.addView(title);
    }

    public void setRightOnClick(View.OnClickListener listener) {
        if (right_text != null) {
            right_text.setOnClickListener(listener);
        }
        if (right_img != null) {
            right_img.setOnClickListener(listener);
        }
    }
}
