package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.activity.ShopDetailsActivity;
import com.sence.bean.response.PSearchBean;
import com.sence.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public class SearchShopAdapter extends RecyclerView.Adapter<SearchShopAdapter.ViewHolder> {
    private Context context;
    private List<PSearchBean.GoodsListBean> list = new ArrayList<>();

    public SearchShopAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PSearchBean.GoodsListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_searchshop,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchShopAdapter.ViewHolder holder, final int position) {
        holder.mName.setText(list.get(position).getName());
        holder.mPrice.setText("￥"+list.get(position).getPrice());
        GlideUtils.getInstance().loadHead(list.get(position).getImg(),holder.mImg);
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


        private ImageView mImg;
        private TextView mName,mPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_name_searchshop);
            mPrice = itemView.findViewById(R.id.tv_price_searchshop);
            mImg = itemView.findViewById(R.id.iv_img_searchshop);
        }
    }
}
