package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PMyInfoBean;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public class SearchShopAdapter extends RecyclerView.Adapter<SearchShopAdapter.ViewHolder> {
    private Context context;
    private List<PMyInfoBean.OtherInfoBean> list = new ArrayList<>();

    public SearchShopAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PMyInfoBean.OtherInfoBean> list){
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
    public void onBindViewHolder(@NonNull SearchShopAdapter.ViewHolder holder, int position) {
//        holder.mName.setText(list.get(position).getNick_name());
//        holder.mPrice.setText(list.get(position).getContent());
//        Glide.with(context)
//                .load(Urls.base_url + list.get(position).getAlbum_url())
//                .placeholder(R.drawable.hint_img)
//                .fallback(R.drawable.hint_img)
//                .into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return 5;
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
