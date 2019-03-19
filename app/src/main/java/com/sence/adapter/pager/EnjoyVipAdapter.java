package com.sence.adapter.pager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.EnjoyVip;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EnjoyVipAdapter extends RecyclerView.Adapter<EnjoyVipAdapter.ViewHolder> {
    private Context context;
    private List<EnjoyVip> list = new ArrayList<>();

    public EnjoyVipAdapter(Context context){
        this.context = context;
    }
    public void setList(List<EnjoyVip> list){
        this.list = list;
    }

    @NonNull
    @Override
    public EnjoyVipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.rv_item_enjoyvip,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EnjoyVipAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageView;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_img_enjoyvip_item);
            name = itemView.findViewById(R.id.tv_name_enjoyvip_item);
        }
    }
}
