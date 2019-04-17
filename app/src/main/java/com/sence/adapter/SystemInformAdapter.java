package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PMyOrderBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SystemInformAdapter extends RecyclerView.Adapter<SystemInformAdapter.ViewHolder> {
    private Context context;
    private List<PMyOrderBean.ListBean.GoodsBean> list = new ArrayList<>();

    public SystemInformAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PMyOrderBean.ListBean.GoodsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SystemInformAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_systeminform, parent, false);
        return new SystemInformAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemInformAdapter.ViewHolder holder, final int position) {
//        holder.mTime.setText( );
//        holder.mType.setText( );
//        holder.mContent.setText( );
        holder.mLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView mTime, mContent, mType;
        private LinearLayout mLook;

        public ViewHolder(View itemView) {
            super(itemView);
            mTime = itemView.findViewById(R.id.tv_time_systeminform);
            mContent = itemView.findViewById(R.id.tv_content_systeminform);
            mType = itemView.findViewById(R.id.tv_type_systeminform);
            mLook = itemView.findViewById(R.id.ll_look_systeminform);
        }
    }

}

