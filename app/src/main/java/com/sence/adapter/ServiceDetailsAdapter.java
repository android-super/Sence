package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sence.R;
import com.sence.bean.response.PMyOrderBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceDetailsAdapter extends RecyclerView.Adapter<ServiceDetailsAdapter.ViewHolder> {
    private Context context;
    private List<PMyOrderBean.ListBean> list = new ArrayList<>();

    public ServiceDetailsAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PMyOrderBean.ListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServiceDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_servicedetails,parent,false);
        return new ServiceDetailsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ServiceDetailsAdapter.ViewHolder holder, int position) {
        ServiceDetailsImgAdapter mServiceDetailsImgAdapter = new ServiceDetailsImgAdapter(context);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(context,3);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        holder.mRecyclerView.setLayoutManager(linearLayoutManager);
        holder.mRecyclerView.setAdapter(mServiceDetailsImgAdapter);
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mImageView;
        private TextView mName,mContent;
        private RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_vimg_servicedetails);
            mName = itemView.findViewById(R.id.tv_vname_servicedetails);
            mContent = itemView.findViewById(R.id.tv_vcontent_servicedetails);
            mRecyclerView = itemView.findViewById(R.id.recycle_servicedetails_img);
        }
    }
}
