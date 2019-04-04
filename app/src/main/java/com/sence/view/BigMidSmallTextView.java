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

    private String left_text;
    private String mid_text;
    private String right_text;
    private int left_size;
    private int mid_size;
    private int right_size;
    private int left_color = 0x000000;
    private int mid_color =  0x000000;
    private int right_color =  0x000000;
    private boolean is_left_bold;
    private boolean is_mid_bold;
    private boolean is_right_bold;

    public BigMidSmallTextView(Context context) {
        this(context,null);
    }

    public BigMidSmallTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        
    }

    public BigMidSmallTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context =context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BigMidSmallTextView);
        left_text = array.getString(R.styleable.BigMidSmallTextView_left_text);
        mid_text = array.getString(R.styleable.BigMidSmallTextView_mid_text);
        right_text = array.getString(R.styleable.BigMidSmallTextView_right_text);
        left_size = array.getDimensionPixelSize(R.styleable.BigMidSmallTextView_left_size,0);
        right_size = array.getDimensionPixelSize(R.styleable.BigMidSmallTextView_right_size,0);
        mid_size = array.getDimensionPixelSize(R.styleable.BigMidSmallTextView_mid_size,0);
        left_color = array.getColor(R.styleable.BigMidSmallTextView_left_color,left_color);
        mid_color = array.getColor(R.styleable.BigMidSmallTextView_mid_color,mid_color);
        right_color = array.getColor(R.styleable.BigMidSmallTextView_right_color,right_color);
        is_left_bold = array.getBoolean(R.styleable.BigMidSmallTextView_left_bold,false);
        is_mid_bold = array.getBoolean(R.styleable.BigMidSmallTextView_mid_bold,false);
        is_right_bold = array.getBoolean(R.styleable.BigMidSmallTextView_right_bold,false);
        array.recycle();
        removeAllViews();
        setOrientation(HORIZONTAL);
        addLeftText();
        addMidText();
        addRightText();
    }

    private void addLeftText() {
        if (left_text==null){
            return;
        }
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(left_text);
        textView.setTextColor(left_color);
        textView.getPaint().setTextSize(left_size);
        if (is_left_bold){
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        addView(textView);
    }

    private void addMidText() {
        if (mid_text==null){
            return;
        }
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(mid_text);
        textView.setTextColor(mid_color);
        textView.getPaint().setTextSize(mid_size);
        if (is_mid_bold){
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        addView(textView);
    }

    private void addRightText() {
        if (left_text==null){
            return;
        }
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(right_text);
        textView.setTextColor(right_color);
        textView.getPaint().setTextSize(right_size);
        if (is_right_bold){
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        addView(textView);
    }

}
