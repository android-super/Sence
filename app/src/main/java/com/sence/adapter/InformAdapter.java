package com.sence.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PMyOrderBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InformAdapter extends RecyclerView.Adapter<InformAdapter.ViewHolder> {
    private Context context;
    private List<PMyOrderBean.ListBean.GoodsBean> list = new ArrayList<>();
    private boolean ifupdown = true;
    private int lineCount;
    private boolean ischai;
    // 介绍是否展开
    private boolean isExpand;
    // 介绍动作runnable
    private Runnable resumeRunnable;
    private Layout layout;
    private String substring;

    public InformAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PMyOrderBean.ListBean.GoodsBean> list) {
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
//        holder.mTime.setText( );
//        holder.mType.setText( );
//        holder.mContent.setText( );
        lineCount = holder.mContent.getLineCount();
        holder.mContent.setMaxLines(2);
        holder.mContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flex(holder);
            }
        });
//        final Drawable drawable = context.getResources().getDrawable(R.drawable.xiangshang);
//        Layout layout = holder.mContent.getLayout();
//        int lineEnd = layout.getLineEnd(0);
//        String substring = holder.mContent.getText().toString().substring(0, lineEnd);
//        Log.i("aaaaaa",substring);
//        ischai = true;
//        ViewTreeObserver vto = holder.mContent.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//
//                // 得到TextView的布局
//                Layout layout = holder.mContent.getLayout();
//                // 得到TextView显示有多少行
//                int lines = holder.mContent.getLineCount();
////                try {
////                    if (isExpand) {
//////                        setContentText(holder.mContent.getText().toString() + "\u3000 ", isExpand, holder);
////                    } else {
////                        if (lines > 2) {
////                            StringBuffer threeLinesContent = new StringBuffer();
////                            for (int i = 0; i < 2; i++) {
////                                threeLinesContent.append(holder.mContent.getText().toString().substring(layout.getLineStart(i), layout.getLineEnd(i)));
////                            }
////                            String textString = threeLinesContent.substring(0, threeLinesContent.length() - 3) + "...\u3000 ";
//////                            setContentText(textString, false, holder);
////                            holder.mContent.setOnClickListener(new View.OnClickListener() {
////
////                                @Override
////                                public void onClick(View v) {
////                                    if (isExpand) {
////                                        isExpand = false;
////                                    } else {
////                                        isExpand = true;
////                                    }
////                                }
////                            });
////                        }
////
////                    }
////                } catch (Exception e) {
////                    // Do nothing
////                }
//            }
//        });
//    }​​
    }
//    private void setContentText(String textContent, boolean expand, ViewHolder holder) {
//    ​SpannableString ss = new SpannableString(textContent);
//        Drawable drawable;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            drawable = context.getResources().getDrawable(expand ? R.drawable.xiangshang : R.drawable.xiangxia, null);
//        } else {
//            drawable = context.getResources().getDrawable(expand ? R.drawable.xiangshang : R.drawable.xiangxia);
//        }
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
//        ss.setSpan(imageSpan, textContent.length() - 1, textContent.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        holder.mContent.setText(ss);
//    }

    private void flex(ViewHolder holder) {
        if (ifupdown) {
            holder.mFlex.setImageResource(R.drawable.xiangxia);
            ifupdown = !ifupdown;
//            holder.mContent.setMaxEms(lineCount);
//            double d = (double) holder.mContent.length() /18;//文本长度除以每行字符长度
//            int  okcprogress = (int) (Math.floor(d))+1;//除数取整，也就是行数
            holder.mContent.setLines(lineCount);//展开全部
        } else {
            holder.mFlex.setImageResource(R.drawable.xiangshang);
            holder.mContent.setLines(2);//收缩为原始两行
            holder.mContent.setText(substring + "…");
            ifupdown = !ifupdown;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
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

