package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sence.R;
import com.sence.activity.WebActivity;
import com.sence.activity.web.WebConstans;
import com.sence.bean.response.PSystemInformBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SystemInformAdapter extends RecyclerView.Adapter<SystemInformAdapter.ViewHolder> {
    private Context context;
    private List<PSystemInformBean> list = new ArrayList<>();

    public SystemInformAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PSystemInformBean> list) {
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
        holder.mTime.setText(list.get(position).getAdd_time());
        holder.mType.setText(list.get(position).getTitle());
        holder.mContent.setText(list.get(position).getContent());
        holder.mLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", list.get(position).getWeb_view());
                intent.putExtra("title", "系统通知");
                intent.putExtra("code", WebConstans.WebCode.XTTZ);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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

