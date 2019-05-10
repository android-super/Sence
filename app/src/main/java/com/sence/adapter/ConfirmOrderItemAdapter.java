package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PBusBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ConfirmOrderItemAdapter extends RecyclerView.Adapter<ConfirmOrderItemAdapter.ViewHolder> {
    private Context context;
    private List<PBusBean.CartBean.GoodsBean> list = new ArrayList<>();

    public ConfirmOrderItemAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PBusBean.CartBean.GoodsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_confirmorderitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        GlideUtils.getInstance().loadHead( list.get(position).getImg(),holder.mImageView);
        holder.mName.setText(list.get(position).getName());
        holder.mPrice.setText("￥" + list.get(position).getPrice());
        holder.mNum.setText("×" + list.get(position).getNum());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private NiceImageView mImageView;
        private TextView mName, mNum, mPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img_confirmorder);
            mName = itemView.findViewById(R.id.tv_shopname_confirmorder);
            mPrice = itemView.findViewById(R.id.tv_price_confirmorder);
            mNum = itemView.findViewById(R.id.tv_num_confirmorder);
        }
    }
}
