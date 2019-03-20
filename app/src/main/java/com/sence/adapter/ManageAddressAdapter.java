package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.MyOrder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.ViewHolder> {
    private Context context;
    private List<MyOrder> list = new ArrayList<>();

    public ManageAddressAdapter(Context context){
        this.context = context;
    }
    public void setList(List<MyOrder> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ManageAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_manageaddress,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ManageAddressAdapter.ViewHolder holder, int position) {
//        Glide.with(context).load(list.get(position).getImg()).into(holder.imageView);
//        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout editor,delete;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name_manageaddress);
            editor = itemView.findViewById(R.id.ll_editor_manageaddress);
            delete = itemView.findViewById(R.id.ll_delete_manageaddress);
        }
    }
}
