package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PBusBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ConfirmOrderAdapter extends RecyclerView.Adapter<ConfirmOrderAdapter.ViewHolder> {
    private Context context;
    private List<PBusBean.CartBean> list = new ArrayList<>();

    public ConfirmOrderAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PBusBean.CartBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ConfirmOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_confirmorder, parent, false);
        return new ConfirmOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmOrderAdapter.ViewHolder holder, final int position) {
        ConfirmOrderItemAdapter confirmOrderItemAdapter = new ConfirmOrderItemAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        holder.mRecyclerView.setLayoutManager(linearLayoutManager);
        holder.mRecyclerView.setAdapter(confirmOrderItemAdapter);
        List<PBusBean.CartBean.GoodsBean> listGoods = new ArrayList<>();
        for (int i = 0; i < list.get(position).getGoods().size(); i++) {
            if(list.get(position).getGoods().get(i).isSelect()){
                listGoods.add(list.get(position).getGoods().get(i));
            }
        }
        if(listGoods.size()>0){
            confirmOrderItemAdapter.setList(listGoods);
        }
        holder.mName.setText(list.get(position).getShopname());
        holder.mNum.setText("共"+list.get(position).getAll_num()+"件商品");
        holder.mPrice.setText("￥:"+list.get(position).getAll_price());
        holder.mPostPrice.setText("￥:"+list.get(position).getAll_postage());
        holder.mMaxPrice.setText("￥:"+list.get(position).getAll_money());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private TextView mName, mNum, mPrice,mPostPrice,mMaxPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.recycle_confirmorderitem);
            mName = itemView.findViewById(R.id.tv_stroename_confirmorder);
            mPrice = itemView.findViewById(R.id.tv_shopprice_confirmorder);
            mNum = itemView.findViewById(R.id.tv_shopnum_confirmorder);
            mPostPrice = itemView.findViewById(R.id.tv_postprice_confirmorder);
            mMaxPrice = itemView.findViewById(R.id.tv_maxprice_confirmorder);
        }
    }

}

