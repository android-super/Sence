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
import com.sence.activity.ConfirmOrderActivity;
import com.sence.activity.OrderDetailsActivity;
import com.sence.bean.response.PMyOrderBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    private Context context;
    private List<PMyOrderBean.ListBean> list = new ArrayList<>();

    public MyOrderAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PMyOrderBean.ListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_myorder,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getGoods().getImg()).placeholder(R.drawable.hint_img).fallback(R.drawable.hint_img).into(holder.mImageView);
        holder.mName.setText(list.get(position).getGoods().getName());
        holder.mTime.setText(list.get(position).getAddtime());
        if(list.get(position).getGoods().getPrice().contains(".")){
            holder.mPprice.setText("￥"+list.get(position).getGoods().getPrice());
            holder.mPrice.setText("￥"+list.get(position).getGoods().getPrice());
        }else{
            holder.mPprice.setText("￥"+list.get(position).getGoods().getPrice()+".00");
            holder.mPrice.setText("￥"+list.get(position).getGoods().getPrice()+".00");
        }
        holder.mPnum.setText("×"+list.get(position).getGoods().getNum());
        holder.mNum.setText("共"+list.get(position).getGoods().getNum()+"件");
        switch (position){
            case 0:
                holder.mState.setText("全部商品");
                break;
            case 1:
                holder.mState.setText("等待支付");
                break;
            case 2:
                holder.mState.setText("等待发货");
                break;
            case 3:
                holder.mState.setText("等待收货");
                break;
            case 4:
                holder.mState.setText("等待评价");
                break;
        }
        holder.mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ConfirmOrderActivity.class));
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
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
        private TextView mName,mPrice,mPnum,mState,mNum,mSevice,mCancel,mAlipay,mTime,mPprice;

        public ViewHolder(View itemView) {
            super(itemView);
            mState = itemView.findViewById(R.id.tv_state_myorder);
            mTime = itemView.findViewById(R.id.tv_time_myorder);
            mImageView = itemView.findViewById(R.id.iv_img_myorder);
            mName = itemView.findViewById(R.id.tv_name_myorder);
            mPprice = itemView.findViewById(R.id.tv_pprice_myorder);
            mPnum = itemView.findViewById(R.id.tv_pnum_myorder);
            mNum = itemView.findViewById(R.id.tv_num_myorder);
            mPrice = itemView.findViewById(R.id.tv_price_myorder);
            mSevice = itemView.findViewById(R.id.tv_service_myorder);
            mCancel = itemView.findViewById(R.id.tv_cancel_myorder);
            mAlipay = itemView.findViewById(R.id.tv_alipay_myorder);
        }
    }
}
