package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.activity.ServiceDetailsActivity;
import com.sence.bean.response.PMyOrderBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCommendAdapter extends RecyclerView.Adapter<ShopCommendAdapter.ViewHolder> {
    private Context context;
    private List<PMyOrderBean.ListBean> list = new ArrayList<>();

    public ShopCommendAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PMyOrderBean.ListBean> list){
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ServiceDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mImageView;
        private TextView mName,mContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img_shopcommend);
            mName = itemView.findViewById(R.id.tv_name_shopcommend);
            mContent = itemView.findViewById(R.id.tv_content_shopcommend);
        }
    }
}
