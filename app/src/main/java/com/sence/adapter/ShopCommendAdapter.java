package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sence.R;
import com.sence.activity.ServiceDetailsActivity;
import com.sence.bean.response.PShopCommendBean;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCommendAdapter extends RecyclerView.Adapter<ShopCommendAdapter.ViewHolder> {
    private Context context;
    private List<PShopCommendBean> list = new ArrayList<>();

    public ShopCommendAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PShopCommendBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopCommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_shopcommend,parent,false);
        return new ShopCommendAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ShopCommendAdapter.ViewHolder holder, int position) {
        holder.mContent.setText(list.get(position).getContent());
        holder.mName.setText(list.get(position).getNickname());
        Glide.with(context)
                .load(list.get(position).getAvatar())
                .placeholder(R.drawable.hint_img)
                .fallback(R.drawable.hint_img)
                .into(holder.mImageView);
        if(list.get(position).getImgs().size()>0){
            Glide.with(context)
                    .load(list.get(position).getImgs().get(0))
                    .placeholder(R.drawable.hint_img)
                    .fallback(R.drawable.hint_img)
                    .into(holder.mCommendImg);
        }else{
            holder.mCommendImg.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ServiceDetailsActivity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImageView;
        private ImageView mCommendImg;
        private TextView mName,mContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img_shopcommend);
            mCommendImg = itemView.findViewById(R.id.iv_commendimg_shopcommend);
            mName = itemView.findViewById(R.id.tv_name_shopcommend);
            mContent = itemView.findViewById(R.id.tv_content_shopcommend);
        }
    }
}
