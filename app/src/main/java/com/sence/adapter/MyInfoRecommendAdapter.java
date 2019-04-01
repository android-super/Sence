package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sence.R;
import com.sence.bean.response.PMyInfoBean;
import com.sence.net.Urls;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyInfoRecommendAdapter extends RecyclerView.Adapter<MyInfoRecommendAdapter.ViewHolder> {
    private Context context;
    private List<PMyInfoBean.OtherInfoBean> list = new ArrayList<>();

    public MyInfoRecommendAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PMyInfoBean.OtherInfoBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyInfoRecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_myinforecommend,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyInfoRecommendAdapter.ViewHolder holder, int position) {
        holder.mName.setText(list.get(position).getNick_name());
        holder.mTime.setText(list.get(position).getAdd_time());
        holder.mTitle.setText(list.get(position).getTitle());
        holder.mContent.setText(list.get(position).getContent());
        holder.mComment.setText(list.get(position).getMessage_count());
        holder.mLike.setText(list.get(position).getPraise_count());
        holder.mShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Glide.with(context)
                .load(Urls.base_url + list.get(position).getAvatar())
                .placeholder(R.drawable.hint_img)
                .fallback(R.drawable.hint_img)
                .into(holder.mImageView);
        Glide.with(context)
                .load(Urls.base_url + list.get(position).getAlbum_url())
                .placeholder(R.drawable.hint_img)
                .fallback(R.drawable.hint_img)
                .into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImageView,mImg;
        private TextView mName,mTitle,mTime,mContent,mLike,mComment;
        private RelativeLayout mShape;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_title_myinforecommend);
            mImageView = itemView.findViewById(R.id.iv_img_myinforecommend);
            mName = itemView.findViewById(R.id.tv_name_myinforecommend);
            mTime = itemView.findViewById(R.id.tv_time_myinforecommend);
            mContent = itemView.findViewById(R.id.tv_content_myinforecommend);
            mImg = itemView.findViewById(R.id.iv_imgshop_myinforecommend);
            mLike = itemView.findViewById(R.id.tv_like_myinforecommend);
            mComment = itemView.findViewById(R.id.tv_comment_myinforecommend);
            mShape = itemView.findViewById(R.id.rl_shape_myinforecommend);
        }
    }
}
