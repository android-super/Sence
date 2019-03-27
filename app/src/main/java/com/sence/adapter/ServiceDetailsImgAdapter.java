package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sence.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceDetailsImgAdapter extends RecyclerView.Adapter<ServiceDetailsImgAdapter.ViewHolder> {
    private Context context;
    private List<String> list = new ArrayList<>();

    public ServiceDetailsImgAdapter(Context context){
        this.context = context;
    }
    public void setList(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServiceDetailsImgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_servicedetailsimg,parent,false);
        return new ServiceDetailsImgAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ServiceDetailsImgAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position))
                .placeholder(R.drawable.hint_img)
                .fallback(R.drawable.hint_img)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img_servicedetailsimg);
        }
    }
}
