package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sence.R;
import com.sence.bean.response.POrderDetailsBean;
import com.sence.net.Urls;
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
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.ViewHolder holder, int position) {

        holder.mName.setText(list.get(position).getName());
        holder.mPrice.setText("￥"+list.get(position).getPrice());
        holder.mNum.setText("х"+list.get(position).getNum());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.hint_img);
        Glide.with(context)
                .load(Urls.base_url + list.get(position).getImg())
                .into(holder.mImg);

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
