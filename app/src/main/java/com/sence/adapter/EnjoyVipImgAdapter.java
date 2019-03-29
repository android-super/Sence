package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sence.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EnjoyVipImgAdapter extends RecyclerView.Adapter<EnjoyVipImgAdapter.ViewHolder> {
    private Context context;
    private int size;
    private int drawable;
    public EnjoyVipImgAdapter(Context context){
        this.context = context;
    }
    public void setList(int size,int drawable){
        this.size = size;
        this.drawable=drawable;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EnjoyVipImgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_enjoyvipimg,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EnjoyVipImgAdapter.ViewHolder holder, int position) {
        holder.mXing.setImageResource(drawable);
    }

    @Override
    public int getItemCount() {
        return size;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mXing;

        public ViewHolder(View itemView) {
            super(itemView);
            mXing = itemView.findViewById(R.id.iv_xing_enjoyvipimg);
        }
    }
}
