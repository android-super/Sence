package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sence.R;
import com.sence.activity.ImgFlexActivity;
import com.sence.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCommendImgAdapter extends RecyclerView.Adapter<ShopCommendImgAdapter.ViewHolder> {
    private Context context;
    private List<String> list = new ArrayList<>();
    private int num;

    public ShopCommendImgAdapter(Context context){
        this.context = context;
    }
    public void setList(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopCommendImgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_shopcommentimg,parent,false);
        return new ShopCommendImgAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ShopCommendImgAdapter.ViewHolder holder, final int position) {
        GlideUtils.getInstance().loadNormal( list.get(position),holder.mImageView);

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImgFlexActivity.class);
                intent.putStringArrayListExtra("imgs", (ArrayList<String>) list);
                intent.putExtra("position",position);
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

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_commendimg_shopcommend);
        }
    }
}
