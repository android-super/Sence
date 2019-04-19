package com.sence.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sence.R;

import androidx.annotation.Nullable;

/**
 * Created by zwy on 2019/4/3.
 * package_name is com.sence.view
 * 描述:SenceGit
 */
public class BigMidSmallTextView extends LinearLayout {
    private Context context;
    private TextView left;
    private TextView right;
    private TextView mid;

    private String left_text;
    private String mid_text;
    private String right_text;
    private int left_size;
    private int mid_size;
    private int right_size;
    private int left_color = 0x000000;
    private int mid_color = 0x000000;
    private int right_color = 0x000000;
    private boolean is_left_bold;
    private boolean is_mid_bold;
    private boolean is_right_bold;

    public BigMidSmallTextView(Context context) {
        this(context, null);
    }

    public BigMidSmallTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public BigMidSmallTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BigMidSmallTextView);
        left_text = array.getString(R.styleable.BigMidSmallTextView_left_text);
        mid_text = array.getString(R.styleable.BigMidSmallTextView_mid_text);
        right_text = array.getString(R.styleable.BigMidSmallTextView_right_text);
        left_size = array.getDimensionPixelSize(R.styleable.BigMidSmallTextView_left_size, 0);
        right_size = array.getDimensionPixelSize(R.styleable.BigMidSmallTextView_right_size, 0);
        mid_size = array.getDimensionPixelSize(R.styleable.BigMidSmallTextView_mid_size, 0);
        left_color = array.getColor(R.styleable.BigMidSmallTextView_left_color, left_color);
        mid_color = array.getColor(R.styleable.BigMidSmallTextView_mid_color, mid_color);
        right_color = array.getColor(R.styleable.BigMidSmallTextView_right_color, right_color);
        is_left_bold = array.getBoolean(R.styleable.BigMidSmallTextView_left_bold, false);
        is_mid_bold = array.getBoolean(R.styleable.BigMidSmallTextView_mid_bold, false);
        is_right_bold = array.getBoolean(R.styleable.BigMidSmallTextView_right_bold, false);
        array.recycle();
        removeAllViews();
        setOrientation(HORIZONTAL);
        addLeftText();
        addMidText();
        addRightText();
    }

    private void addLeftText() {
        if (left_text == null) {
            return;
        }
        left = new TextView(context);
        left.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        left.setText(left_text);
        left.setTextColor(left_color);
        left.getPaint().setTextSize(left_size);
        if (is_left_bold) {
            left.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        addView(left);
    }

    private void addMidText() {
        if (mid_text == null) {
            return;
        }
        mid = new TextView(context);
        mid.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mid.setText(mid_text);
        mid.setTextColor(mid_color);
        mid.getPaint().setTextSize(mid_size);
        if (is_mid_bold) {
            mid.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        addView(mid);
    }

    private void addRightText() {
        if (right_text == null) {
            return;
        }
        right = new TextView(context);
        right.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        right.setText(right_text);
        right.setTextColor(right_color);
        right.getPaint().setTextSize(right_size);
        if (is_right_bold) {
            right.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        addView(right);
    }

    public void setLeft_text(String left_text) {
        left.setText(left_text);
        invalidate();
    }

    public void setRight_text(String right_text) {
        right.setText(right_text);
        invalidate();
    }

    public void setMid_text(String mid_text) {
        mid.setText(mid_text);
        invalidate();
    }
}
