package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sence.R;
import com.sence.bean.response.PShopDetailsBean;
import com.sence.net.Urls;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopDetailsCommendAdapter extends RecyclerView.Adapter<ShopDetailsCommendAdapter.ViewHolder> {
    private Context context;
    private List<PShopDetailsBean.CommentBean> list = new ArrayList<>();

    public ShopDetailsCommendAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PShopDetailsBean.CommentBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopDetailsCommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_shopdetails,parent,false);
        return new ShopDetailsCommendAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ShopDetailsCommendAdapter.ViewHolder holder, int position) {
        holder.mName.setText(list.get(position).getNickname());
        holder.mContent.setText(list.get(position).getContent());
        Glide.with(context).load(Urls.base_url + list.get(position).getImg()).placeholder(R.drawable.hint_img).fallback(R.drawable.hint_img).into(holder.mImageView);
        Glide.with(context).load(Urls.base_url + list.get(position).getAvatar()).placeholder(R.drawable.hint_img).fallback(R.drawable.hint_img).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mImageView;
        private NiceImageView mImg;
        private TextView mName,mContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img_shopdetails);
            mImg = itemView.findViewById(R.id.iv_shopcommentimg_shopdetails);
            mName = itemView.findViewById(R.id.tv_name_shopdetailscommend);
            mContent = itemView.findViewById(R.id.tv_content_shopdetailscommend);
        }
    }
}
