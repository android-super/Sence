package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sence.R;
import com.sence.activity.ShopDetailsActivity;
import com.sence.bean.response.PUserMyInfoBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyInfoShopListAdapter extends RecyclerView.Adapter<MyInfoShopListAdapter.ViewHolder> {
    private Context context;
    private List<PUserMyInfoBean.GoodsInfoBean> list = new ArrayList<>();

    public MyInfoShopListAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PUserMyInfoBean.GoodsInfoBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyInfoShopListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vp_shopitem_myinfo,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyInfoShopListAdapter.ViewHolder holder, int position) {
        GlideUtils.getInstance().loadNormal(list.get(position).getImg(),holder.mImageView);
        holder.mName.setText(list.get(position).getName());
        holder.mPrice.setText("￥ " +list.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopDetailsActivity.class);
                intent.putExtra("id", list.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImageView;
        private TextView mName,mPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (NiceImageView) itemView.findViewById(R.id.iv_img_myinfo);
            mName = (TextView) itemView.findViewById(R.id.tv_shopname_myinfo);
            mPrice = (TextView) itemView.findViewById(R.id.tv_price_myinfo);
        }
    }
}
