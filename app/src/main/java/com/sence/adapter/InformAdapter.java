package com.sence.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PInformBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InformAdapter extends RecyclerView.Adapter<InformAdapter.ViewHolder> {
    private Context context;
    private List<PInformBean> list = new ArrayList<>();
    private boolean ifupdown = true;
    private int lineCount;
    // 介绍是否展开
    private boolean isExpand;
    // 介绍动作runnable

    public InformAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PInformBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InformAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_inform, parent, false);
        return new InformAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InformAdapter.ViewHolder holder, final int position) {
        holder.mTime.setText(list.get(position).getAdd_time());
        holder.mType.setText(list.get(position).getTitle());
        holder.mContent.setText(list.get(position).getContent());
        lineCount = holder.mContent.getLineCount();
//        holder.mContent.setMaxLines(2);
        holder.mContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (null != holder.mContent && !TextUtils.isEmpty(holder.mContent.getText().toString())) {
                    GetLineContent(holder, position);
                }
            }
        });
        holder.mContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).isIsflex()) {
                    list.get(position).setIsflex(false);
                } else {
                    list.get(position).setIsflex(true);
                }
                GetLineContent(holder, position);
//                flex(holder);
            }
        });

    }

    private void GetLineContent(final InformAdapter.ViewHolder holder, int position) {

        // 得到TextView的布局
        Layout layout = holder.mContent.getLayout();
        // 得到TextView显示有多少行
        int lines = holder.mContent.getLineCount();
        try {
            if (list.get(position).isIsflex()) {
                setContentText(list.get(position).getContent() + "  \u3000", isExpand, holder);
            } else {
                if (lines > 2) {
                    StringBuffer threeLinesContent = new StringBuffer();
                    for (int i = 0; i < 2; i++) {
                        threeLinesContent.append(holder.mContent.getText().toString().substring(layout.getLineStart(i), layout.getLineEnd(i)));
                    }
                    String textString = threeLinesContent.substring(0, threeLinesContent.length() - 3) + "...  \u3000";
                    setContentText(textString, false, holder);


                }

            }
        } catch (Exception e) {
//                     Do nothing
        }
    }

    private void setContentText(String textContent, boolean expand, InformAdapter.ViewHolder holder) {

        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = context.getResources().getDrawable(expand ? R.drawable.xiangshang : R.drawable.xiangxia, null);
        } else {
            drawable = context.getResources().getDrawable(expand ? R.drawable.xiangshang : R.drawable.xiangxia);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        SpannableString ss = new SpannableString(textContent);
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        ss.setSpan(imageSpan, textContent.length() - 1, textContent.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        Log.i("aaaaaa", ss.toString());
        holder.mContent.setText(ss);
    }


//    }​


    private void flex(ViewHolder holder) {
        if (ifupdown) {
            holder.mFlex.setImageResource(R.drawable.xiangxia);
            ifupdown = !ifupdown;
            holder.mContent.setLines(lineCount);//展开全部
        } else {
            holder.mFlex.setImageResource(R.drawable.xiangshang);
            holder.mContent.setLines(2);//收缩为原始两行
            ifupdown = !ifupdown;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mFlex;
        private TextView mTime, mContent, mType;

        public ViewHolder(View itemView) {
            super(itemView);
            mFlex = itemView.findViewById(R.id.iv_flex_inform);
            mTime = itemView.findViewById(R.id.tv_time_inform);
            mContent = itemView.findViewById(R.id.tv_content_inform);
            mType = itemView.findViewById(R.id.tv_type_inform);
        }
    }

}

