package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.activity.ImgFlexActivity;
import com.sence.bean.response.PShopDetailsBean;
import com.sence.utils.GlideUtils;
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
    public void onBindViewHolder(@NonNull ShopDetailsCommendAdapter.ViewHolder holder, final int position) {
        holder.mName.setText(list.get(position).getNickname());
        holder.mContent.setText(list.get(position).getContent());
        GlideUtils.getInstance().loadNormal( list.get(position).getImg(),holder.mImageView);
        GlideUtils.getInstance().loadHead( list.get(position).getAvatar(),holder.mImg);
        final String img = list.get(position).getImg();
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImgFlexActivity.class);
                intent.putExtra("img",img);
                context.startActivity(intent);
            }
        });
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
