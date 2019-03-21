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
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getGoods().getImg()).placeholder(R.drawable.hint_img).fallback(R.drawable.hint_img).into(holder.imageView);
        holder.name.setText(list.get(position).getGoods().getName());
        holder.time.setText(list.get(position).getAddtime());
        if(list.get(position).getGoods().getPrice().contains(".")){
            holder.pprice.setText("￥"+list.get(position).getGoods().getPrice());
            holder.price.setText("￥"+list.get(position).getGoods().getPrice());
        }
        holder.pprice.setText("￥"+list.get(position).getGoods().getPrice()+".00");
        holder.price.setText("￥"+list.get(position).getGoods().getPrice()+".00");
        holder.pnum.setText("×"+list.get(position).getGoods().getNum());
        holder.num.setText("共"+list.get(position).getGoods().getNum()+"件");
        switch (position){
            case 0:
                holder.state.setText("全部商品");
                break;
            case 1:
                holder.state.setText("等待支付");
                break;
            case 2:
                holder.state.setText("等待发货");
                break;
            case 3:
                holder.state.setText("等待收货");
                break;
            case 4:
                holder.state.setText("等待评价");
                break;
        }
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ConfirmOrderActivity.class));
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, OrderDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageView;
        private TextView name,price,pnum,state,num,sevice,cancel,alipay,time,pprice;

        public ViewHolder(View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.tv_state_myorder);
            time = itemView.findViewById(R.id.tv_time_myorder);
            imageView = itemView.findViewById(R.id.iv_img_myorder);
            name = itemView.findViewById(R.id.tv_name_myorder);
            pprice = itemView.findViewById(R.id.tv_pprice_myorder);
            pnum = itemView.findViewById(R.id.tv_pnum_myorder);
            num = itemView.findViewById(R.id.tv_num_myorder);
            price = itemView.findViewById(R.id.tv_price_myorder);
            sevice = itemView.findViewById(R.id.tv_service_myorder);
            cancel = itemView.findViewById(R.id.tv_cancel_myorder);
            alipay = itemView.findViewById(R.id.tv_alipay_myorder);
        }
    }
}
