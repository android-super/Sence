package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sence.R;
import com.sence.activity.ShopDetailsActivity;
import com.sence.bean.response.POrderDetailsBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {
    private Context context;
    private List<POrderDetailsBean.GoodsBean> list = new ArrayList<>();

    public OrderDetailsAdapter(Context context){
        this.context = context;
    }
    public void setList(List<POrderDetailsBean.GoodsBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_orderdetails,parent,false);
        return new OrderDetailsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.ViewHolder holder, final int position) {

        holder.mName.setText(list.get(position).getName());
        if(list.get(position).getPrice().contains(".")){
            holder.mPrice.setText("￥"+list.get(position).getPrice());
        }else{
            holder.mPrice.setText("￥"+list.get(position).getPrice()+".00");
        }
        holder.mNum.setText("х"+list.get(position).getNum());
        GlideUtils.getInstance().loadNormal( list.get(position).getImg(),holder.mImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopDetailsActivity.class);
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


        private NiceImageView mImg;
        private TextView mName,mPrice,mNum;

        public ViewHolder(View itemView) {
            super(itemView);

            mImg = itemView.findViewById(R.id.iv_img_orderdetails);
            mName = itemView.findViewById(R.id.tv_shopname_orderdetails);
            mPrice = itemView.findViewById(R.id.tv_price_orderdetails);
            mNum = itemView.findViewById(R.id.tv_num_orderdetails);
        }
    }
}
